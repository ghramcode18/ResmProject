package The.Geeks.ResmProject.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import The.Geeks.ResmProject.domain.Address;
import The.Geeks.ResmProject.domain.City;
import The.Geeks.ResmProject.domain.Country;
import The.Geeks.ResmProject.domain.Image;
import The.Geeks.ResmProject.domain.ImageStatus;
import The.Geeks.ResmProject.domain.Property;
import The.Geeks.ResmProject.domain.PropertyImage;
import The.Geeks.ResmProject.domain.Region;
import The.Geeks.ResmProject.domain.Role;
import The.Geeks.ResmProject.domain.User;
import The.Geeks.ResmProject.domain.UserFav;
import The.Geeks.ResmProject.domain.UserImage;
import The.Geeks.ResmProject.message.ResponseMessage;
import The.Geeks.ResmProject.payload.request.AddPropertyToFavoriteListRequest;
import The.Geeks.ResmProject.payload.request.ProfileEditRequest;
import The.Geeks.ResmProject.payload.request.SingUpRequest;
import The.Geeks.ResmProject.payload.response.ProfilePropertyView;
import The.Geeks.ResmProject.payload.response.PropertyView;
import The.Geeks.ResmProject.payload.response.ResponseInfo;
import The.Geeks.ResmProject.payload.response.ViewProfile;
import The.Geeks.ResmProject.payload.response.ViewPropertyFavoriteListResponse;
import The.Geeks.ResmProject.payload.response.address;
import The.Geeks.ResmProject.payload.response.city;
import The.Geeks.ResmProject.payload.response.country;
import The.Geeks.ResmProject.payload.response.region;
import The.Geeks.ResmProject.repo.AddressRepo;
import The.Geeks.ResmProject.repo.CityRepo;
import The.Geeks.ResmProject.repo.CountryRepo;
import The.Geeks.ResmProject.repo.ImageRepository;
import The.Geeks.ResmProject.repo.ImageStatusRepo;
import The.Geeks.ResmProject.repo.PropertyImageRepo;
import The.Geeks.ResmProject.repo.PropertyRepo;
import The.Geeks.ResmProject.repo.RegionRepo;
import The.Geeks.ResmProject.repo.RoleRepo;
import The.Geeks.ResmProject.repo.UserFavRepo;
import The.Geeks.ResmProject.repo.UserImageRepo;
import The.Geeks.ResmProject.repo.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Component
@JsonSerialize
@JsonPOJOBuilder
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final AddressRepo addressRepo;
    private final RegionRepo regionRepo;
    private final CityRepo cityRepo;
    private final CountryRepo countryRepo;
    private final UserFavRepo userFavRepo;
    private final PropertyImageRepo propertyImageRepo;
    private final ImageRepository imageRepo;
    private final ImageStatusRepo imageStatusRepo;
    private final UserImageRepo userImageRepo;
    private final PropertyRepo propertyRepo;
    private final RoleRepo roleRepo;
    private final MailService mailService;

    Pageable pageable = PageRequest.of(0, 10, Sort.by("propertyId").descending());

    @Override
    public ResponseEntity<ResponseMessage> addPropertyToFavoriteList(
            AddPropertyToFavoriteListRequest addPropertyToFavoriteListRequest) {

        try {

            // here decode token and checkIfUserExist in db
            DecodeToken dtoken = decodeToken(addPropertyToFavoriteListRequest.getToken());

            User user = userRepo.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));

            // here check if property exist in db
            String PropertyId = addPropertyToFavoriteListRequest.getPropertyId();
            Integer PropertyIdInt = Integer.parseInt(PropertyId);

            Optional<Property> newProperty = propertyRepo.findById((long) PropertyIdInt);

            if (newProperty.isPresent()) {

                UserFav userfav = new UserFav();
                userfav.setProperty(newProperty.get());
                userfav.setUser(user);
                userFavRepo.save(userfav);

                ResponseMessage responseMessage = new ResponseMessage();

                responseMessage.setSuccessful(true);
                responseMessage.setError("");
                return ResponseEntity.ok(responseMessage);
            } else {
                ResponseMessage responseMessage = new ResponseMessage();
                responseMessage.setSuccessful(false);
                responseMessage.setError("Property is not found");
                return ResponseEntity.ok(responseMessage);
            }

        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setSuccessful(false);
            responseMessage.setError(e.getMessage());
            return ResponseEntity.ok(responseMessage);

        }
    }

    public ViewPropertyFavoriteListResponse viewPropertyFavoriteList(String token) {

        try {

            // here decode token and checkIfUserExist in db
            DecodeToken dtoken = decodeToken(token);

            User user = userRepo.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));

            ViewPropertyFavoriteListResponse viewPropertyFavoriteListResponse = new ViewPropertyFavoriteListResponse();
            UserFav userfav = new UserFav();
            userfav.setUser(user);

            List<UserFav> userFavorites = user.getUserPropertyFavList();

            ResponseInfo responseInfo = new ResponseInfo();
            List<PropertyView> propertiesList = new ArrayList<PropertyView>();
            List<String> imagesUrlList = new ArrayList<String>();

            for (int i = 0; i < userFavorites.size(); i++) {
                UserFav userFavorite = new UserFav();
                userFavorite.setProperty(userFavorites.get(i).getProperty());

                // here i setPropertyView with genral data without address and image
                PropertyView propertyView = new PropertyView();
                PropertyView propertyView2 = setPropertyView(propertyView, userFavorite);

                // here i am trying to remove properties with status not exist
                if (!(propertyView2.equals(new PropertyView()))) {

                    // here i setPropertyView with address
                    propertyView2.setAddress(setAddresses(userFavorite));

                    List<PropertyImage> propertyImage = propertyImageRepo
                            .findByPropertyId(userFavorite.getProperty().getPropertyId());

                    for (int j = 0; j < propertyImage.size(); j++) {

                        Optional<Image> image = imageRepo.findById(propertyImage.get(j).getImage().getId());

                        String url = image.get().getUrl();
                        imagesUrlList.add(url);
                        propertyView2.setImagesUrlList(imagesUrlList);

                    }

                    imagesUrlList = new ArrayList<>();

                    propertiesList.add(propertyView2);

                }
            }
            responseInfo.setPropertiesList(propertiesList);

            viewPropertyFavoriteListResponse.setResponseInfo(responseInfo);
            viewPropertyFavoriteListResponse.setSuccessful(true);
            viewPropertyFavoriteListResponse.setError("");

            return viewPropertyFavoriteListResponse;

        } catch (Exception e) {
            ViewPropertyFavoriteListResponse viewPropertyFavoriteListResponse = new ViewPropertyFavoriteListResponse();

            viewPropertyFavoriteListResponse.setSuccessful(false);

            viewPropertyFavoriteListResponse.setError(e.getMessage());

            return viewPropertyFavoriteListResponse;

        }

    }

    public PropertyView setPropertyView(PropertyView propertyView, UserFav userFavorite) {

        propertyView.setPropertyId(userFavorite.getProperty().getPropertyId());
        propertyView.setDescription(userFavorite.getProperty().getDescription());
        propertyView.setNumBathrooms(userFavorite.getProperty().getNumBathrooms());
        propertyView.setNumStoreys(userFavorite.getProperty().getNumStoreys());
        propertyView.setNumRooms(userFavorite.getProperty().getNumRooms());
        propertyView.setSpace(userFavorite.getProperty().getSpace());
        propertyView.setPrice(userFavorite.getProperty().getPrice());
        propertyView.setDateAdded(userFavorite.getProperty().getDateAdded());
        propertyView.setCategory(userFavorite.getProperty().getPropertyCategory().getCategory());

        if (userFavorite.getProperty().getPropertyStatus().getStatus().equals("NOTEXIST")) {

            return new PropertyView();
        }
        return propertyView;

    }

    public List<ProfilePropertyView> setProperty(List<Property> property) {
        List<ProfilePropertyView> profilePropertyViews = new ArrayList<ProfilePropertyView>();

        for (int i = 0; i < property.size(); i++) {
            ProfilePropertyView profilePropertyView = new ProfilePropertyView();
            profilePropertyView.setDescription(property.get(i).getDescription());
            profilePropertyView.setNumBathrooms(property.get(i).getNumBathrooms());
            profilePropertyView.setNumRooms(property.get(i).getNumRooms());
            profilePropertyView.setNumStoreys(property.get(i).getNumStoreys());
            profilePropertyView.setSpace(property.get(i).getSpace());
            profilePropertyView.setDateAdded(property.get(i).getDateAdded());
            profilePropertyView.setCategory(property.get(i).getPropertyCategory().getCategory());
            profilePropertyView.setAddress(setAddress(property.get(i).getAddress()));
            profilePropertyView.setImagesUrlList(imageRepo.findByImagePropertyId(property.get(i).getPropertyId()));
            if (property.get(i).getPropertyStatus().getStatus().equals("NOTEXIST")) {

                profilePropertyView = new ProfilePropertyView();
            }
            profilePropertyViews.add(profilePropertyView);
            
        }

        return profilePropertyViews;
    }

    address setAddresses(UserFav userFavorite) {

        Optional<Property> newProperty = propertyRepo.findById((long) userFavorite.getProperty()
                .getPropertyId());
        address address = new address();

        address.setAddressDescription(newProperty.get().getAddress().getAddressDescription());
        address.setLattitude(newProperty.get().getAddress().getLattitude());
        address.setLongitutde(newProperty.get().getAddress().getLongitutde());
        region region = new region();
        region.setName(newProperty.get().getAddress().getRegion().getName());
        city city = new city();
        city.setName(newProperty.get().getAddress().getRegion().getCity().getName());
        country country = new country();
        country.setName(newProperty.get().getAddress().getRegion().getCity().getCountry().getName());
        city.setCountry(country);
        region.setCity(city);

        address.setRegion(region);

        return address;
    }

    //convert Entity to dto
    address setAddress(Address address) {

        address newaddress = new address();

        newaddress.setAddressDescription(address.getAddressDescription());
        newaddress.setLattitude(address.getLattitude());
        newaddress.setLongitutde(address.getLongitutde());
        region region = new region();
        region.setName(address.getRegion().getName());
        city city = new city();
        city.setName(address.getRegion().getCity().getName());
        country country = new country();
        country.setName(address.getRegion().getCity().getCountry().getName());
        city.setCountry(country);
        region.setCity(city);
        newaddress.setRegion(region);

        return newaddress;
    }

    // method to decodeToken
    private DecodeToken decodeToken(String token) throws UnsupportedEncodingException {

        DecodeToken dtoken = DecodeToken.getDecoded(token);

        System.out.println(dtoken);

        return dtoken;
    }
    //////////////////////////////////////////////////////

    private User setUser(SingUpRequest singUpRequest) {

        User newUser = new User();
        newUser.setUsername(singUpRequest.getSingUpInfoRequest().getUsername());
        newUser.setPhoneNumber(singUpRequest.getSingUpInfoRequest().getPhoneNumber());
        newUser.setFirstName(singUpRequest.getSingUpInfoRequest().getFirstName());
        newUser.setLastName(singUpRequest.getSingUpInfoRequest().getLastName());
        newUser.setPassword(singUpRequest.getSingUpInfoRequest().getPassword());

        return newUser;
    }

    @Override
    public ResponseEntity<ResponseMessage> singUp(
            @RequestBody SingUpRequest singUpRequest)
            throws Exception {

        try {
            User newUser = setUser(singUpRequest);

            Country country = new Country();
            country = countryRepo.findByName(
                    singUpRequest.getSingUpInfoRequest()
                            .getAddress().getRegion().getCity().getCountry().getName());

            City city = new City();
            city = cityRepo.findByName(singUpRequest.getSingUpInfoRequest()
                    .getAddress().getRegion().getCity().getName());

            Region region = new Region();
            region = regionRepo.findByName(singUpRequest.getSingUpInfoRequest()
                    .getAddress().getRegion().getName());

            address address = singUpRequest.getSingUpInfoRequest().getAddress();
            Address newAddress = addressRepo.findByRegionName(address.getRegion().name);

            newAddress.setLattitude(address.getLattitude());
            newAddress.setLongitutde(address.getLongitutde());
            newAddress.setAddressDescription(address.getAddressDescription());
            city.setCountry(country);
            region.setCity(city);
            newAddress.setRegion(region);

            newUser.setAddress(newAddress);
            userRepo.save(newUser);
            mailService.authEmail(newUser.getUsername(),
                    "please verify your email by this link : \nhttp://localhost:8090/api/v1/verify/"
                            + newUser.getUserId(),
                    "thanks for register in our platform");
            ResponseMessage responseMessage = new ResponseMessage();

            responseMessage.setSuccessful(true);
            responseMessage.setError("");

            return ResponseEntity.ok(responseMessage);
        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setSuccessful(false);
            responseMessage.setError(e.getMessage());
            return ResponseEntity.ok(responseMessage);
        }

    }

    private User profileEdit(ProfileEditRequest profileEditRequest) throws UnsupportedEncodingException {

        DecodeToken dtoken = decodeToken(profileEditRequest.getToken());
        User user = userRepo.findByUsername(
                dtoken.getSub()).get();

        user.setUsername(profileEditRequest.getProfileEditInfoRequest().getUsername());
        user.setPassword(profileEditRequest.getProfileEditInfoRequest().getPassword());
        user.setFirstName(profileEditRequest.getProfileEditInfoRequest().getFirstName());
        user.setLastName(profileEditRequest.getProfileEditInfoRequest().getLastName());
        user.setPhoneNumber(profileEditRequest.getProfileEditInfoRequest().getPhoneNumber());

        return user;

    }

    private String userDirectory = "src/main/resources/static";

    public ResponseEntity uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {
        String fileName = StringUtils.cleanPath(userDirectory + "/" + file.getOriginalFilename());
        Path path = Paths.get(fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("")
                .path(fileName)
                .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }

    public ResponseEntity multiUpload(@RequestParam("files") MultipartFile[] files) {
        List<Object> fileDownloadUrls = new ArrayList<>();
        Arrays.asList(
                files)
                .stream()
                .forEach(file -> fileDownloadUrls.add(uploadToLocalFileSystem(file).getBody()));
        return ResponseEntity.ok(fileDownloadUrls);

    }

    private List<Image> setImages(MultipartFile[] files, ProfileEditRequest profileEditRequest) {

        Object fileDownloadUrls = multiUpload(files).getBody().toString();
        System.out.println(fileDownloadUrls);

        List<String> myList = new ArrayList<String>(Arrays.asList((multiUpload(
                files).getBody()
                .toString()).split(",")));
        System.out.println(myList);

        List<Image> images = new ArrayList<Image>();
        for (int i = 0; i < myList.size(); i++) {
            Image image1 = new Image();
            image1.setUrl(myList.get(i));
            image1.setDateAdded(profileEditRequest.getProfileEditInfoRequest().getDateAdded());
            images.add(image1);
            imageRepo.save(image1);
        }

        return images;
    }

    private UserImage setUserImage(List<Image> images, User newUser) {
        Optional<ImageStatus> imageStatus = imageStatusRepo.findById((long) 1);
        UserImage userImage = new UserImage();

        System.out.println(images.size());
        for (int i = 0; i < images.size(); i++) {

            userImage = new UserImage();
            userImage.setImageStatus(imageStatus.get());
            userImage.setImage(images.get(i));
            userImage.setUser(newUser);
            userImageRepo.save(userImage);
        }

        return userImage;
    }

    @Override
    public ResponseEntity<ResponseMessage> profileEdit(
            @RequestParam("files") MultipartFile[] files,
            @RequestPart("profileEditRequest") ProfileEditRequest profileEditRequest)
            throws UnsupportedEncodingException, Exception {

        try {
            DecodeToken dtoken = decodeToken(profileEditRequest.getToken());
            User user = userRepo.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));

            user = profileEdit(profileEditRequest);
            userRepo.save(user);

            List<Image> images = setImages(files, profileEditRequest);
            UserImage userImage = setUserImage(images, user);

            ResponseMessage responseMessage = new ResponseMessage();

            responseMessage.setSuccessful(true);
            responseMessage.setError("");

            return ResponseEntity.ok(responseMessage);

        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setSuccessful(false);
            responseMessage.setError(e.getMessage());
            return ResponseEntity.ok(responseMessage);

        }

    }

    @Override
    public ViewProfile viewProfile(String token) {
        try {
            DecodeToken dtoken = decodeToken(token);
            User user = userRepo.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));

            ViewProfile viewProfile = new ViewProfile();
            viewProfile.getProfileInfo().setUsername(user.getUsername());
            viewProfile.getProfileInfo().setFirstName(user.getFirstName());
            viewProfile.getProfileInfo().setLastName(user.getLastName());
            viewProfile.getProfileInfo().setAddress(setAddress(user.getAddress()));
            viewProfile.getProfileInfo().setPropertiesList(setProperty(propertyRepo.findByUserId(user.getUserId())));

            viewProfile.setSuccessful(true);
            viewProfile.setError("");
            return viewProfile;

        } catch (Exception e) {
            ViewProfile viewProfile = new ViewProfile();
            viewProfile.setSuccessful(false);
            viewProfile.setError(e.getMessage());
            return viewProfile;

        }
    }

    public String verify(Long id) throws Exception {
        User user = userRepo.findById(id).orElseThrow(() -> new Exception("no user with this id"));
        Optional<Role> role = roleRepo.findById((long) 1);
        user.setRole(role.get());
        userRepo.save(user);
        return "user with id : " + id + " is verified";
    }

    @Override
    public List<UserFav> findPaginated(int pageNo, int pageSize) {
        // TODO Auto-generated method stub
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<UserFav> pagedResult = userFavRepo.findAll(paging);

        return pagedResult.toList();
    }

}

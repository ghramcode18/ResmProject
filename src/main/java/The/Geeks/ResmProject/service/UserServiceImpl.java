package The.Geeks.ResmProject.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import The.Geeks.ResmProject.domain.Address;
import The.Geeks.ResmProject.domain.City;
import The.Geeks.ResmProject.domain.Country;
import The.Geeks.ResmProject.domain.Image;
import The.Geeks.ResmProject.domain.Property;
import The.Geeks.ResmProject.domain.PropertyImage;
import The.Geeks.ResmProject.domain.Region;
import The.Geeks.ResmProject.domain.Role;
import The.Geeks.ResmProject.domain.User;
import The.Geeks.ResmProject.domain.UserFav;
import The.Geeks.ResmProject.message.ResponseMessage;
import The.Geeks.ResmProject.payload.request.AddPropertyToFavoriteListRequest;
import The.Geeks.ResmProject.payload.request.SingUpRequest;
import The.Geeks.ResmProject.payload.response.PropertyView;
import The.Geeks.ResmProject.payload.response.ResponseInfo;
import The.Geeks.ResmProject.payload.response.ViewPropertyFavoriteListResponse;
import The.Geeks.ResmProject.payload.response.address;
import The.Geeks.ResmProject.payload.response.city;
import The.Geeks.ResmProject.payload.response.country;
import The.Geeks.ResmProject.payload.response.region;
import The.Geeks.ResmProject.repo.AddressRepo;
import The.Geeks.ResmProject.repo.CityRepo;
import The.Geeks.ResmProject.repo.CountryRepo;
import The.Geeks.ResmProject.repo.ImageRepository;
import The.Geeks.ResmProject.repo.PropertyImageRepo;
import The.Geeks.ResmProject.repo.PropertyRepo;
import The.Geeks.ResmProject.repo.RegionRepo;
import The.Geeks.ResmProject.repo.RoleRepo;
import The.Geeks.ResmProject.repo.UserFavRepo;
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
    private final RoleRepo roleRepo;

    @Autowired
    UserRepo userRepository;

    @Autowired
    PropertyRepo propertyRepo;

    @Autowired
    MailService mailService;

    @Override
    public ResponseEntity<ResponseMessage> addPropertyToFavoriteList(
            AddPropertyToFavoriteListRequest addPropertyToFavoriteListRequest) {

        Message message = new Message();
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

        Message message = new Message();
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
            responseInfo.setPropertiesList(propertiesList);

            viewPropertyFavoriteListResponse.setResponseInfo(responseInfo);
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setSuccessful(true);
            responseMessage.setError("");
            viewPropertyFavoriteListResponse.setResponseMessage(responseMessage);

            return viewPropertyFavoriteListResponse;

        } catch (Exception e) {
            ViewPropertyFavoriteListResponse viewPropertyFavoriteListResponse = new ViewPropertyFavoriteListResponse();
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setSuccessful(false);
            responseMessage.setError(e.getMessage());
            viewPropertyFavoriteListResponse.setResponseMessage(responseMessage);
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

        return propertyView;

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

    // method to decodeToken
    private DecodeToken decodeToken(String token) throws UnsupportedEncodingException {

        DecodeToken dtoken = DecodeToken.getDecoded(token);

        System.out.println(dtoken);

        return dtoken;
    }
    //////////////////////////////////////////////////////

    @Override
    public ResponseEntity<ResponseMessage> singUp(
            @RequestBody SingUpRequest singUpRequest) throws Exception {

        Message message = new Message();

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
                    "please verify your email by this link : \nhttp://localhost:8090/api/v1/verify/"+ newUser.getUserId(),"thanks for register in our platform");
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
    
    public String verify(Long id) throws Exception {
        User user = userRepo.findById(id).orElseThrow(() -> new Exception("no user with this id"));
        Optional<Role> role = roleRepo.findById((long)1);
        user.setRole(role.get());
        userRepo.save(user);
        return "user with id : " + id + " is verified";
    }

    private User setUser(SingUpRequest singUpRequest) {

        User newUser = new User();

        newUser.setUsername(singUpRequest.getSingUpInfoRequest().getUsername());
        newUser.setPhoneNumber(singUpRequest.getSingUpInfoRequest().getPhoneNumber());
        newUser.setFirstName(singUpRequest.getSingUpInfoRequest().getFirstName());
        newUser.setLastName(singUpRequest.getSingUpInfoRequest().getLastName());
        newUser.setPassword(singUpRequest.getSingUpInfoRequest().getPassword());

        return newUser;
    }

}

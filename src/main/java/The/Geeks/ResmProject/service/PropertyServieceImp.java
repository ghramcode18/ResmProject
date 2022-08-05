package The.Geeks.ResmProject.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.util.StringUtils;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import The.Geeks.ResmProject.domain.Address;
import The.Geeks.ResmProject.domain.City;
import The.Geeks.ResmProject.domain.Country;
import The.Geeks.ResmProject.domain.Image;
import The.Geeks.ResmProject.domain.ImageStatus;
import The.Geeks.ResmProject.domain.Property;
import The.Geeks.ResmProject.domain.PropertyCategory;
import The.Geeks.ResmProject.domain.PropertyImage;
import The.Geeks.ResmProject.domain.PropertyStatus;
import The.Geeks.ResmProject.domain.Region;
import The.Geeks.ResmProject.domain.User;
import The.Geeks.ResmProject.domain.UserFav;
import The.Geeks.ResmProject.message.ResponseMessage;
import The.Geeks.ResmProject.model.propertyRequestModel;
import The.Geeks.ResmProject.payload.request.DeletePropertyFromFvaoriteLsitRequest;
import The.Geeks.ResmProject.payload.request.DeletePropertyRequest;
import The.Geeks.ResmProject.payload.request.HomePageReqest;
import The.Geeks.ResmProject.payload.request.PropertyRequest;
import The.Geeks.ResmProject.payload.request.search.SearchDateAddedRequest;
import The.Geeks.ResmProject.payload.request.search.SearchNumBathroomsRequest;
import The.Geeks.ResmProject.payload.request.search.SearchNumRoomsRequest;
import The.Geeks.ResmProject.payload.request.search.SearchNumStoreysRequest;
import The.Geeks.ResmProject.payload.request.search.SearchPriceRequest;
import The.Geeks.ResmProject.payload.request.search.SearchPropertyCategoryRequest;
import The.Geeks.ResmProject.payload.request.search.SearchSpaceRequest;
import The.Geeks.ResmProject.payload.request.search.SearchUserRequest;
import The.Geeks.ResmProject.payload.response.SearchResponce;
import The.Geeks.ResmProject.payload.response.ViewHomePage;
import The.Geeks.ResmProject.payload.response.HomePageInfo;
import The.Geeks.ResmProject.payload.response.PropertyView;
import The.Geeks.ResmProject.payload.response.ResponseInfo;
import The.Geeks.ResmProject.payload.response.ViewPropertyListResponse;
import The.Geeks.ResmProject.payload.response.address;
import The.Geeks.ResmProject.payload.response.city;
import The.Geeks.ResmProject.payload.response.country;
import The.Geeks.ResmProject.payload.response.region;
import The.Geeks.ResmProject.repo.AddressRepo;
import The.Geeks.ResmProject.repo.CityRepo;
import The.Geeks.ResmProject.repo.CountryRepo;
import The.Geeks.ResmProject.repo.ImageRepository;
import The.Geeks.ResmProject.repo.ImageStatusRepo;
import The.Geeks.ResmProject.repo.PropertyCategoryRepo;
import The.Geeks.ResmProject.repo.PropertyImageRepo;
import The.Geeks.ResmProject.repo.PropertyRepo;
import The.Geeks.ResmProject.repo.PropertyStatusRepo;
import The.Geeks.ResmProject.repo.RegionRepo;
import The.Geeks.ResmProject.repo.UserFavRepo;
import The.Geeks.ResmProject.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import java.nio.file.Files;

@RequiredArgsConstructor
@Component
@Service
public class PropertyServieceImp implements PropertyService {

    @Autowired
    UserRepo userRepository;

    @Autowired
    PropertyImageRepo propertyImageRepo;

    @Autowired
    PropertyRepo propertyRepo;

    @Autowired
    PropertyCategoryRepo propertyCategoryRepo;

    @Autowired
    PropertyStatusRepo propertyStatusRepo;

    @Autowired
    ImageStatusRepo imageStatusRepo;
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    AddressRepo addressRepository;

    @Autowired
    CountryRepo countryRepo;

    @Autowired
    CityRepo cityRepo;
    @Autowired
    ImageRepository imageRepo;

    @Autowired
    RegionRepo regionRepo;

    @Autowired
    UserFavRepo userFavRepo;

    Pageable pageable = 
    PageRequest.of(0, 3, Sort.by("propertyId"));

    @Override
    public ResponseEntity<ResponseMessage> addProperty(
            @RequestParam("files") MultipartFile[] files,
            @RequestPart("propertyRequest") PropertyRequest propertyRequest)
            throws UnsupportedEncodingException, Exception {

        Message message = new Message();
        try {

            // here decode token and checkIfUserExist in db
            DecodeToken dtoken = decodeToken(propertyRequest.getToken());

            User user = userRepository.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));
            // here set property to db
            Property newProperty = setProperty(propertyRequest);

            // here set user to property
            newProperty.setUser(user);

            Optional<PropertyStatus> propertyStatus = propertyStatusRepo
                    .findById((long) 1);
            newProperty.setPropertyStatus(propertyStatus.get());

            // here i am trying setAddress to property
            newProperty.getPropertyStatus();
            Country country = new Country();
            country = countryRepo.findByName(
                    propertyRequest.getPropertyInfo()
                            .getAddress().getRegion().getCity().getCountry().getName());

            City city = new City();
            city = cityRepo.findByName(propertyRequest.getPropertyInfo()
                    .getAddress().getRegion().getCity().getName());

            Region region = new Region();
            region = regionRepo.findByName(propertyRequest.getPropertyInfo()
                    .getAddress().getRegion().getName());

            address address = propertyRequest.getPropertyInfo().getAddress();
            Address newAddress = addressRepository.findByRegionName(address.getRegion().name);
            newAddress.setLattitude(address.getLattitude());
            newAddress.setLongitutde(address.getLongitutde());
            newAddress.setAddressDescription(address.getAddressDescription());
            city.setCountry(country);
            region.setCity(city);
            newAddress.setRegion(region);

            newProperty.setAddress(newAddress);

            // here saving properties in db

            propertyRepo.save(newProperty);

            // here i am trying upload images to local file system and save url in db
            List<Image> images = setImages(files, propertyRequest);

            // here i set imageStatus and set propertyImage
            PropertyImage propertyImage = setPropertyImage(images, newProperty);

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

    address setAddresses(Property Property) {

        Property newProperty = propertyRepo.findByPropertyId((long) Property
                .getPropertyId());
        address address = new address();

        address.setAddressDescription(newProperty.getUser().getAddress().getAddressDescription());
        address.setLattitude(newProperty.getUser().getAddress().getLattitude());
        address.setLongitutde(newProperty.getUser().getAddress().getLongitutde());
        region region = new region();
        region.setName(newProperty.getUser().getAddress().getRegion().getName());
        city city = new city();
        city.setName(newProperty.getUser().getAddress().getRegion().getCity().getName());
        country country = new country();
        country.setName(newProperty.getUser().getAddress().getRegion().getCity().getCountry().getName());
        city.setCountry(country);
        region.setCity(city);

        address.setRegion(region);

        return address;
    }

    // method to setImageStatus
    private PropertyImage setPropertyImage(List<Image> images, Property newProperty) {
        Optional<ImageStatus> imageStatus = imageStatusRepo.findById((long) 1);
        PropertyImage propertyImage = new PropertyImage();

        System.out.println(images.size());
        for (int i = 0; i < images.size(); i++) {

            propertyImage = new PropertyImage();
            propertyImage.setImageStatus(imageStatus);
            propertyImage.setImage(images.get(i));
            propertyImage.setProperty(newProperty);
            propertyImageRepo.save(propertyImage);
        }

        return propertyImage;
    }

    private String userDirectory = "src/main/resources/static";

    // method to store images in file system
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

    // method to upload multi image and return url for each image
    public ResponseEntity multiUpload(@RequestParam("files") MultipartFile[] files) {
        List<Object> fileDownloadUrls = new ArrayList<>();
        Arrays.asList(
                files)
                .stream()
                .forEach(file -> fileDownloadUrls.add(uploadToLocalFileSystem(file).getBody()));
        return ResponseEntity.ok(fileDownloadUrls);

    }

    // method to set url and dataAdded image in db
    private List<Image> setImages(MultipartFile[] files, PropertyRequest propertyRequest) {

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
            image1.setDateAdded(propertyRequest.getPropertyInfo().getDateAdded());

            images.add(image1);
            imageRepository.save(image1);

        }

        return images;
    }

    private List<Image> setImagesforEdit(MultipartFile[] files, propertyRequestModel propertyRequest) {

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
            image1.setDateAdded(propertyRequest.getNewPropertyInfo().getDateAdded());

            images.add(image1);
            imageRepository.save(image1);

        }

        return images;
    }

    // public void zipDownload(@RequestParam List<String> name, HttpServletResponse
    // response) throws IOException {
    // ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
    // for (String fileName : name) {
    // FileSystemResource resource = new FileSystemResource(fileBasePath +
    // fileName);
    // ZipEntry zipEntry = new ZipEntry(resource.getFilename());
    // zipEntry.setSize(resource.contentLength());
    // zipOut.putNextEntry(zipEntry);
    // StreamUtils.copy(resource.getInputStream(), zipOut);
    // zipOut.closeEntry();
    // }
    // }

    // method to decodeToken
    private DecodeToken decodeToken(String token) throws UnsupportedEncodingException {

        DecodeToken dtoken = DecodeToken.getDecoded(token);

        System.out.println(dtoken);

        return dtoken;
    }

    // method to set propertyRequest in propertie db
    private Property setProperty(PropertyRequest propertyRequest) {

        Property newProperty = new Property();
        newProperty.setDateAdded(propertyRequest.getPropertyInfo().getDateAdded());
        newProperty.setDescription(propertyRequest.getPropertyInfo().getDescription());
        newProperty.setNumBathrooms(propertyRequest.getPropertyInfo().getNumBathrooms());
        newProperty.setNumRooms(propertyRequest.getPropertyInfo().getNumRooms());
        newProperty.setNumStoreys(propertyRequest.getPropertyInfo().getNumStoreys());
        newProperty.setPrice(propertyRequest.getPropertyInfo().getPrice());
        newProperty.setSpace(propertyRequest.getPropertyInfo().getSpace());

        PropertyCategory propertyCategory = propertyCategoryRepo
                .findByCategory(propertyRequest.getPropertyInfo().getCategory());
        newProperty.setPropertyCategory(propertyCategory);

        return newProperty;
    }

    private Property editProperty(propertyRequestModel propertyRequest) {

        Property newProperty;
        Long PropertyIdInt = Long.parseLong(propertyRequest.getPropertyId());
        newProperty = propertyRepo.findByPropertyId((Long) PropertyIdInt);

        System.out.println("helo ghram here3");

        newProperty.setDateAdded(propertyRequest.getNewPropertyInfo().getDateAdded());
        newProperty.setDescription(propertyRequest.getNewPropertyInfo().getDescription());
        newProperty.setNumBathrooms(propertyRequest.getNewPropertyInfo().getNumBathrooms());
        newProperty.setNumRooms(propertyRequest.getNewPropertyInfo().getNumRooms());
        newProperty.setNumStoreys(propertyRequest.getNewPropertyInfo().getNumStoreys());
        newProperty.setPrice(propertyRequest.getNewPropertyInfo().getPrice());
        newProperty.setSpace(propertyRequest.getNewPropertyInfo().getSpace());

        System.out.println("helo ghram here4");

        PropertyCategory propertyCategory = propertyCategoryRepo
                .findByCategory(propertyRequest.getNewPropertyInfo().getCategory());
        newProperty.setPropertyCategory(propertyCategory);

        Optional<PropertyStatus> propertyStatus = propertyStatusRepo
                .findById((long) 1);
        newProperty.setPropertyStatus(propertyStatus.get());

        return newProperty;
    }

    @Override
    public SearchResponce searchPrice(SearchPriceRequest searchPriceRequest) throws UnsupportedEncodingException {

        Message message = new Message();
        try {

            // here decode token and checkIfUserExist in db
            DecodeToken dtoken = decodeToken(searchPriceRequest.getSearchRequestInfo().getToken());

            User user = userRepository.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));

            List<Property> propertiesList = propertyRepo.findByPrice(
                    searchPriceRequest.getSearchRequestInfo().getPrice());

            List<PropertyView> propertiesList2 = new ArrayList<PropertyView>();

            ResponseInfo responseInfo = new ResponseInfo();
            List<String> imagesUrlList = new ArrayList<String>();

            for (int i = 0; i < propertiesList.size(); i++) {

                Property property = new Property();
                property = propertiesList.get(i);

                // here i setPropertyView with genral data without address and image
                PropertyView propertyView = new PropertyView();
                PropertyView propertyView2 = setPropertyView(propertyView, property);

                if (!(propertyView2.equals(new PropertyView()))) {

                    // here i setPropertyView with address
                    propertyView2.setAddress(setAddresses(property));

                    List<PropertyImage> propertyImage = propertyImageRepo
                            .findByPropertyId(property.getPropertyId());

                    for (int j = 0; j < propertyImage.size(); j++) {

                        Optional<Image> image = imageRepo.findById(propertyImage.get(j).getImage().getId());

                        String url = image.get().getUrl();
                        imagesUrlList.add(url);
                        propertyView2.setImagesUrlList(imagesUrlList);

                    }

                    imagesUrlList = new ArrayList<>();

                    propertiesList2.add(propertyView2);

                }
            }
            responseInfo.setPropertiesList(propertiesList2);
            SearchResponce searchResponce = new SearchResponce();
            searchResponce.setResponseInfo(responseInfo);

            searchResponce.setSuccessful(true);
            searchResponce.setError("");

            return searchResponce;

        } catch (Exception e) {
            SearchResponce searchResponce = new SearchResponce();
            searchResponce.setSuccessful(false);
            searchResponce.setError(e.getMessage());
            return searchResponce;
        }

    }

    public PropertyView setPropertyView(PropertyView propertyView, Property property) {
        propertyView.setPropertyId(property.getPropertyId());
        propertyView.setDescription(property.getDescription());
        propertyView.setNumBathrooms(property.getNumBathrooms());
        propertyView.setNumStoreys(property.getNumStoreys());
        propertyView.setNumRooms(property.getNumRooms());
        propertyView.setSpace(property.getSpace());
        propertyView.setPrice(property.getPrice());
        propertyView.setDateAdded(property.getDateAdded());
        propertyView.setCategory(property.getPropertyCategory().getCategory());
        if (property.getPropertyStatus().getStatus().equals("NOTEXIST")) {

            return new PropertyView();
        }

        return propertyView;

    }

    @Override
    public SearchResponce searchSpace(@RequestBody SearchSpaceRequest searchSpaceRequest)
            throws UnsupportedEncodingException {

        Message message = new Message();
        try {

            // here decode token and checkIfUserExist in db
            DecodeToken dtoken = decodeToken(searchSpaceRequest.getSearchSpaceRequestInfo().getToken());

            User user = userRepository.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));
            List<Property> propertiesList = propertyRepo.findBySpace(
                    searchSpaceRequest.getSearchSpaceRequestInfo().getSpace());

            List<PropertyView> propertiesList2 = new ArrayList<PropertyView>();

            ViewPropertyListResponse viewPropertyFavoriteListResponse = new ViewPropertyListResponse();

            ResponseInfo responseInfo = new ResponseInfo();
            List<String> imagesUrlList = new ArrayList<String>();

            for (int i = 0; i < propertiesList.size(); i++) {

                Property property = new Property();
                property = propertiesList.get(i);

                // here i setPropertyView with genral data without address and image
                PropertyView propertyView = new PropertyView();
                PropertyView propertyView2 = setPropertyView(propertyView, property);

                if (!(propertyView2.equals(new PropertyView()))) {

                    // here i setPropertyView with address
                    propertyView2.setAddress(setAddresses(property));

                    List<PropertyImage> propertyImage = propertyImageRepo
                            .findByPropertyId(property.getPropertyId());

                    for (int j = 0; j < propertyImage.size(); j++) {

                        Optional<Image> image = imageRepo.findById(propertyImage.get(j).getImage().getId());

                        String url = image.get().getUrl();
                        imagesUrlList.add(url);
                        propertyView2.setImagesUrlList(imagesUrlList);

                    }

                    imagesUrlList = new ArrayList<>();

                    propertiesList2.add(propertyView2);
                }
            }
            responseInfo.setPropertiesList(propertiesList2);
            SearchResponce searchResponce = new SearchResponce();
            searchResponce.setResponseInfo(responseInfo);

            searchResponce.setSuccessful(true);
            searchResponce.setError("");

            return searchResponce;

        } catch (Exception e) {
            SearchResponce searchResponce = new SearchResponce();
            searchResponce.setSuccessful(false);
            searchResponce.setError(e.getMessage());
            return searchResponce;
        }

    }

    @Override
    public SearchResponce searchNumRooms(@RequestBody SearchNumRoomsRequest searchNumRoomsRequest)
            throws UnsupportedEncodingException {

        Message message = new Message();
        try {

            // here decode token and checkIfUserExist in db
            DecodeToken dtoken = decodeToken(searchNumRoomsRequest.getSearchNumRoomsRequestInfo().getToken());

            User user = userRepository.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));
            List<Property> propertiesList = propertyRepo.findByNumRooms(
                    searchNumRoomsRequest.getSearchNumRoomsRequestInfo().getNumRooms());

            List<PropertyView> propertiesList2 = new ArrayList<PropertyView>();

            ResponseInfo responseInfo = new ResponseInfo();
            List<String> imagesUrlList = new ArrayList<String>();

            for (int i = 0; i < propertiesList.size(); i++) {

                Property property = new Property();
                property = propertiesList.get(i);

                // here i setPropertyView with genral data without address and image
                PropertyView propertyView = new PropertyView();
                PropertyView propertyView2 = setPropertyView(propertyView, property);
                if (!(propertyView2.equals(new PropertyView()))) {

                    // here i setPropertyView with address
                    propertyView2.setAddress(setAddresses(property));

                    List<PropertyImage> propertyImage = propertyImageRepo
                            .findByPropertyId(property.getPropertyId());

                    for (int j = 0; j < propertyImage.size(); j++) {

                        Optional<Image> image = imageRepo.findById(propertyImage.get(j).getImage().getId());

                        String url = image.get().getUrl();
                        imagesUrlList.add(url);
                        propertyView2.setImagesUrlList(imagesUrlList);

                    }

                    imagesUrlList = new ArrayList<>();

                    propertiesList2.add(propertyView2);

                }
            }
            responseInfo.setPropertiesList(propertiesList2);
            SearchResponce searchResponce = new SearchResponce();
            searchResponce.setResponseInfo(responseInfo);

            searchResponce.setSuccessful(true);
            searchResponce.setError("");

            return searchResponce;

        } catch (Exception e) {
            SearchResponce searchResponce = new SearchResponce();
            searchResponce.setSuccessful(false);
            searchResponce.setError(e.getMessage());
            return searchResponce;
        }

    }

    @Override
    public SearchResponce searchNumStoreys(@RequestBody SearchNumStoreysRequest searchnumStoreysRequest)
            throws UnsupportedEncodingException {

        Message message = new Message();
        try {

            // here decode token and checkIfUserExist in db
            DecodeToken dtoken = decodeToken(searchnumStoreysRequest.getSearchNumStoreysRequestInfo().getToken());

            User user = userRepository.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));
            List<Property> propertiesList = propertyRepo.findByNumStoreys(
                    searchnumStoreysRequest.getSearchNumStoreysRequestInfo().getNumStoreys());

            List<PropertyView> propertiesList2 = new ArrayList<PropertyView>();

            ResponseInfo responseInfo = new ResponseInfo();
            List<String> imagesUrlList = new ArrayList<String>();

            for (int i = 0; i < propertiesList.size(); i++) {

                Property property = new Property();
                property = propertiesList.get(i);

                // here i setPropertyView with genral data without address and image
                PropertyView propertyView = new PropertyView();
                PropertyView propertyView2 = setPropertyView(propertyView, property);
                if (!(propertyView2.equals(new PropertyView()))) {

                    // here i setPropertyView with address
                    propertyView2.setAddress(setAddresses(property));

                    List<PropertyImage> propertyImage = propertyImageRepo
                            .findByPropertyId(property.getPropertyId());

                    for (int j = 0; j < propertyImage.size(); j++) {

                        Optional<Image> image = imageRepo.findById(propertyImage.get(j).getImage().getId());

                        String url = image.get().getUrl();
                        imagesUrlList.add(url);
                        propertyView2.setImagesUrlList(imagesUrlList);

                    }

                    imagesUrlList = new ArrayList<>();

                    propertiesList2.add(propertyView2);
                }
            }
            responseInfo.setPropertiesList(propertiesList2);
            SearchResponce searchResponce = new SearchResponce();
            searchResponce.setResponseInfo(responseInfo);

            searchResponce.setSuccessful(true);
            searchResponce.setError("");

            return searchResponce;

        } catch (Exception e) {
            SearchResponce searchResponce = new SearchResponce();
            searchResponce.setSuccessful(false);
            searchResponce.setError(e.getMessage());
            return searchResponce;
        }

    }

    @Override
    public SearchResponce searchNumBathrooms(@RequestBody SearchNumBathroomsRequest searchNumBathroomsRequest)
            throws UnsupportedEncodingException {

        Message message = new Message();
        try {

            // here decode token and checkIfUserExist in db
            DecodeToken dtoken = decodeToken(searchNumBathroomsRequest.getSearchNumBathroomsRequestInfo().getToken());

            User user = userRepository.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));
            List<Property> propertiesList = propertyRepo.findByNumBathrooms(
                    searchNumBathroomsRequest.getSearchNumBathroomsRequestInfo().getNumBathrooms());

            List<PropertyView> propertiesList2 = new ArrayList<PropertyView>();

            ResponseInfo responseInfo = new ResponseInfo();
            List<String> imagesUrlList = new ArrayList<String>();

            for (int i = 0; i < propertiesList.size(); i++) {

                Property property = new Property();
                property = propertiesList.get(i);

                // here i setPropertyView with genral data without address and image
                PropertyView propertyView = new PropertyView();
                PropertyView propertyView2 = setPropertyView(propertyView, property);
                if (!(propertyView2.equals(new PropertyView()))) {

                    // here i setPropertyView with address
                    propertyView2.setAddress(setAddresses(property));

                    List<PropertyImage> propertyImage = propertyImageRepo
                            .findByPropertyId(property.getPropertyId());

                    for (int j = 0; j < propertyImage.size(); j++) {

                        Optional<Image> image = imageRepo.findById(propertyImage.get(j).getImage().getId());

                        String url = image.get().getUrl();
                        imagesUrlList.add(url);
                        propertyView2.setImagesUrlList(imagesUrlList);

                    }

                    imagesUrlList = new ArrayList<>();

                    propertiesList2.add(propertyView2);
                }
            }
            responseInfo.setPropertiesList(propertiesList2);
            SearchResponce searchResponce = new SearchResponce();
            searchResponce.setResponseInfo(responseInfo);

            searchResponce.setSuccessful(true);
            searchResponce.setError("");

            return searchResponce;

        } catch (Exception e) {
            SearchResponce searchResponce = new SearchResponce();
            searchResponce.setSuccessful(false);
            searchResponce.setError(e.getMessage());
            return searchResponce;
        }

    }

    @Override
    public SearchResponce searchDateAdded(
            @RequestBody SearchDateAddedRequest searchDateAddedRequest)
            throws UnsupportedEncodingException {

        Message message = new Message();
        try {

            // here decode token and checkIfUserExist in db
            DecodeToken dtoken = decodeToken(searchDateAddedRequest.getSearchDateAddedRequestInfo().getToken());

            User user = userRepository.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));

            List<Property> propertiesList = new ArrayList<Property>();
            // LocalDate date =
            // LocalDate.parse(searchDateAddedRequest.getSearchDateAddedRequestInfo().getDateAdded());

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(
                    "u-M-d H:m:s[.[SSSSSSSSS][SSSSSSSS][SSSSSSS][SSSSSS][SSSSS][SSSS][SSS][SS][S]]", Locale.ENGLISH);
            String strDateTime = searchDateAddedRequest.getSearchDateAddedRequestInfo().getDateAdded();
            LocalDateTime date = LocalDateTime.parse(strDateTime, dtf);
            propertiesList = propertyRepo.findByDateAdded(date);

            List<PropertyView> propertiesList2 = new ArrayList<PropertyView>();

            ResponseInfo responseInfo = new ResponseInfo();
            List<String> imagesUrlList = new ArrayList<String>();

            for (int i = 0; i < propertiesList.size(); i++) {

                Property property = new Property();
                property = propertiesList.get(i);

                // here i setPropertyView with genral data without address and image
                PropertyView propertyView = new PropertyView();
                PropertyView propertyView2 = setPropertyView(propertyView, property);

                if (!(propertyView2.equals(new PropertyView()))) {

                    // here i setPropertyView with address
                    propertyView2.setAddress(setAddresses(property));

                    List<PropertyImage> propertyImage = propertyImageRepo
                            .findByPropertyId(property.getPropertyId());

                    for (int j = 0; j < propertyImage.size(); j++) {

                        Optional<Image> image = imageRepo.findById(propertyImage.get(j).getImage().getId());

                        String url = image.get().getUrl();
                        imagesUrlList.add(url);
                        propertyView2.setImagesUrlList(imagesUrlList);

                    }

                    imagesUrlList = new ArrayList<>();

                    propertiesList2.add(propertyView2);

                }
            }
            responseInfo.setPropertiesList(propertiesList2);
            SearchResponce searchResponce = new SearchResponce();
            searchResponce.setResponseInfo(responseInfo);

            searchResponce.setSuccessful(true);
            searchResponce.setError("");

            return searchResponce;

        } catch (Exception e) {
            SearchResponce searchResponce = new SearchResponce();
            searchResponce.setSuccessful(false);
            searchResponce.setError(e.getMessage());
            return searchResponce;
        }

    }

    @Override
    public SearchResponce searchPropertyCategory(
            @RequestBody SearchPropertyCategoryRequest searchPropertyCategoryRequest)
            throws UnsupportedEncodingException {

        Message message = new Message();
        try {

            // here decode token and checkIfUserExist in db
            DecodeToken dtoken = decodeToken(
                    searchPropertyCategoryRequest.getSearchPropertyCategoryRequestInfo().getToken());

            User user = userRepository.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));

            PropertyCategory propertyCategory = propertyCategoryRepo
                    .findByCategory(
                            searchPropertyCategoryRequest.getSearchPropertyCategoryRequestInfo().getCategory());

            List<Property> propertiesList = propertyRepo.findByPropertyCategoryId(
                    propertyCategory.getPropertyCategoryId());
            List<PropertyView> propertiesList2 = new ArrayList<PropertyView>();

            ResponseInfo responseInfo = new ResponseInfo();
            List<String> imagesUrlList = new ArrayList<String>();

            for (int i = 0; i < propertiesList.size(); i++) {

                Property property = new Property();
                property = propertiesList.get(i);

                // here i setPropertyView with genral data without address and image
                PropertyView propertyView = new PropertyView();
                PropertyView propertyView2 = setPropertyView(propertyView, property);
                if (!(propertyView2.equals(new PropertyView() ))) {

                // here i setPropertyView with address
                propertyView2.setAddress(setAddresses(property));

                List<PropertyImage> propertyImage = propertyImageRepo
                        .findByPropertyId(property.getPropertyId());

                for (int j = 0; j < propertyImage.size(); j++) {

                    Optional<Image> image = imageRepo.findById(propertyImage.get(j).getImage().getId());

                    String url = image.get().getUrl();
                    imagesUrlList.add(url);
                    propertyView2.setImagesUrlList(imagesUrlList);

                }

                imagesUrlList = new ArrayList<>();

                propertiesList2.add(propertyView2);

            }}
            responseInfo.setPropertiesList(propertiesList2);
            SearchResponce searchResponce = new SearchResponce();
            searchResponce.setResponseInfo(responseInfo);

            searchResponce.setSuccessful(true);
            searchResponce.setError("");

            return searchResponce;

        } catch (Exception e) {
            SearchResponce searchResponce = new SearchResponce();
            searchResponce.setSuccessful(false);
            searchResponce.setError(e.getMessage());
            return searchResponce;
        }

    }

    @Override
    public SearchResponce searchUser(@RequestBody SearchUserRequest searchUserRequest)
            throws UnsupportedEncodingException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<ResponseMessage> editProperty(
            @RequestParam("files") MultipartFile[] files,
            @RequestPart("propertyRequestModel") propertyRequestModel propertyRequestModel)
            throws UnsupportedEncodingException, Exception {

        Message message = new Message();
        try {

            // here decode token and checkIfUserExist in db
            DecodeToken dtoken = decodeToken(propertyRequestModel.getToken());

            User user = userRepository.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));
            // here set property to db

            Property newProperty = editProperty(propertyRequestModel);

            // here set user to property
            newProperty.setUser(user);

            Country country = new Country();
            country = countryRepo.findByName(
                    propertyRequestModel.getNewPropertyInfo()
                            .getAddress().getRegion().getCity().getCountry().getName());

            City city = new City();
            city = cityRepo.findByName(propertyRequestModel.getNewPropertyInfo()
                    .getAddress().getRegion().getCity().getName());

            Region region = new Region();
            region = regionRepo.findByName(propertyRequestModel.getNewPropertyInfo()
                    .getAddress().getRegion().getName());

            address address = propertyRequestModel.getNewPropertyInfo().getAddress();
            Address newAddress = addressRepository.findByRegionName(address.getRegion().name);
            newAddress.setLattitude(address.getLattitude());
            newAddress.setLongitutde(address.getLongitutde());
            newAddress.setAddressDescription(address.getAddressDescription());
            city.setCountry(country);
            region.setCity(city);
            newAddress.setRegion(region);

            newProperty.setAddress(newAddress);

            // here saving properties in db
            propertyRepo.save(newProperty);

            // here i am trying upload images to local file system and save url in db
            List<Image> images = setImagesforEdit(files, propertyRequestModel);

            // here i set imageStatus and set propertyImage
            PropertyImage propertyImage = setPropertyImage(images, newProperty);

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

    public ResponseEntity<ResponseMessage> deleteProperty(@RequestBody DeletePropertyRequest deletePropertyRequest)
            throws UnsupportedEncodingException {

        Message message = new Message();
        try {
            // here decode token and checkIfUserExist in db
            DecodeToken dtoken = decodeToken(deletePropertyRequest.getToken());

            User user = userRepository.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));

            Long id = Long.parseLong(deletePropertyRequest.getPropertyToDeleteId());
            Property property = propertyRepo.findByPropertyId(id);
            Optional<PropertyStatus> propertyStatus = propertyStatusRepo
                    .findById((long) 0);

            property.setPropertyStatus(propertyStatus.get());
            propertyRepo.save(property);
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

    public ResponseEntity<ResponseMessage> deletePropertyFromFavaoriteList(
            @RequestBody DeletePropertyFromFvaoriteLsitRequest deletePropertyRequest)
            throws UnsupportedEncodingException {
        Message message = new Message();
        try {
            // here decode token and checkIfUserExist in db
            DecodeToken dtoken = decodeToken(deletePropertyRequest.getToken());

            User user = userRepository.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));
            Long id = Long.parseLong(deletePropertyRequest.getPropertyId());
            List<UserFav> userFavorites = user.getUserPropertyFavList();

            for (int i = 0; i < userFavorites.size(); i++) {
                if (userFavorites.get(i).getProperty().getPropertyId().equals(id)) {
                    userFavRepo.deleteByPropertyId(id);
                }
            }

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

    private Page<PropertyView> setPropertyToPropertyView(Page<Property> ppage) {
        List<PropertyView> propertyViews = new ArrayList<PropertyView>();
        for(int i = 0 ; i < ppage.getContent().size() ; i++){
            PropertyView propertyView = new PropertyView();
            propertyView.setPropertyId(ppage.getContent().get(i).getPropertyId());
            propertyView.setDescription(ppage.getContent().get(i).getDescription());
            propertyView.setNumBathrooms(ppage.getContent().get(i).getNumBathrooms());
            propertyView.setNumRooms(ppage.getContent().get(i).getNumRooms());
            propertyView.setPrice(ppage.getContent().get(i).getPrice());
            propertyView.setSpace(ppage.getContent().get(i).getSpace());
            propertyView.setCategory(ppage.getContent().get(i).getPropertyCategory().getCategory());
            propertyView.setDateAdded(ppage.getContent().get(i).getDateAdded());
            propertyView.setAddress(setAddress(ppage.getContent().get(i).getAddress()));

            propertyViews.add(propertyView);
    }
    final Page<PropertyView> page = new PageImpl<PropertyView>(propertyViews);
    return page;
        }
        
    

    @Override
    public ViewHomePage viewHomePage(HomePageReqest homePageReqest) throws UnsupportedEncodingException{
        try {
            DecodeToken dtoken = decodeToken(homePageReqest.getToken());

            User user = userRepository.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));

            ViewHomePage viewHomePage = new ViewHomePage();
            Page<PropertyView> propertyView =setPropertyToPropertyView(propertyRepo.findAll(pageable));
            viewHomePage.getHomePageInfo().setPropertyView(propertyView);
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setSuccessful(true);
            responseMessage.setError("");
            viewHomePage.setResponseMessage(responseMessage);

            return viewHomePage;

        } catch (Exception e) {
            ViewHomePage viewHomePage = new ViewHomePage();
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setSuccessful(false);
            responseMessage.setError(e.getMessage());
            viewHomePage.setResponseMessage(responseMessage);
            return viewHomePage;

        }
    }

    // to store image in db
    // @Autowired
    // private FileStorageService storageService;

    // public ResponseEntity<ResponseMessage> uploadFile(MultipartFile file) {
    // String message = "";
    // try {
    // storageService.store(file);

    // message = "Uploaded the file successfully: " + file.getOriginalFilename();
    // return ResponseEntity.status(HttpStatus.OK).body(new
    // ResponseMessage(message));
    // } catch (Exception e) {
    // message = "Could not upload the file: " + file.getOriginalFilename() + "!";
    // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new
    // ResponseMessage(message));
    // }
    // }

}

package The.Geeks.ResmProject.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpHeaders;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.util.StringUtils;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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
import The.Geeks.ResmProject.domain.UserImage;
import The.Geeks.ResmProject.message.ResponseFile;
import The.Geeks.ResmProject.message.ResponseMessage;
import The.Geeks.ResmProject.model.propertyRequestModel;
import The.Geeks.ResmProject.payload.request.PropertyRequest;
import The.Geeks.ResmProject.payload.response.address;
import The.Geeks.ResmProject.payload.response.city;
import The.Geeks.ResmProject.payload.response.country;
import The.Geeks.ResmProject.payload.response.region;
import The.Geeks.ResmProject.repo.AddressRepo;
import The.Geeks.ResmProject.repo.CityRepo;
import The.Geeks.ResmProject.repo.CountryRepo;
import The.Geeks.ResmProject.repo.ImageRepository;
// import The.Geeks.ResmProject.repo.FileDBRepository;
import The.Geeks.ResmProject.repo.ImageStatusRepo;
import The.Geeks.ResmProject.repo.PropertyCategoryRepo;
import The.Geeks.ResmProject.repo.PropertyImageRepo;
import The.Geeks.ResmProject.repo.PropertyRepo;
import The.Geeks.ResmProject.repo.PropertyStatusRepo;
import The.Geeks.ResmProject.repo.RegionRepo;
import The.Geeks.ResmProject.repo.UserRepo;
import The.Geeks.ResmProject.service.DecodeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import java.nio.file.Files;

import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
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
    RegionRepo regionRepo;

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
        newProperty = propertyRepo.findByPropertyId((Long)PropertyIdInt);

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
    public List<Property> searchPrice(Float price) {
        // TODO Auto-generated method stub

        List<Property> propertiesList = propertyRepo.findByPrice(price);

        return propertiesList;
    }

    @Override
    public List<Property> searchSpace(Float space) {
        // TODO Auto-generated method stub
        List<Property> propertiesList = propertyRepo.findBySpace(space);

        return propertiesList;
    }

    @Override
    public List<Property> searchNumRooms(Integer numRooms) {
        // TODO Auto-generated method stub
        List<Property> propertiesList = propertyRepo.findByNumRooms(numRooms);

        return propertiesList;
    }

    @Override
    public List<Property> searchNumStoreys(Integer numStoreys) {
        // TODO Auto-generated method stub
        List<Property> propertiesList = propertyRepo.findByNumStoreys(numStoreys);

        return propertiesList;
    }

    @Override
    public List<Property> searchNumBathrooms(Integer numBathrooms) {
        // TODO Auto-generated method stub
        List<Property> propertiesList = propertyRepo.findByNumBathrooms(numBathrooms);

        return propertiesList;
    }

    @Override
    public List<Property> searchDateAdded(String dateAdded) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Property> searchPropertyCategory(Integer propertyCategory) {
        // TODO Auto-generated method stub
        List<Property> propertiesList = propertyRepo.findByPropertyCategory(propertyCategory);

        return propertiesList;
    }

    @Override
    public List<Property> searchUser(String userName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<ResponseMessage> editProperty(
            @RequestParam("files") MultipartFile[] files,
            @RequestPart("propertyRequestModel") propertyRequestModel propertyRequestModel)
            throws UnsupportedEncodingException, Exception {
        // TODO Auto-generated method stub

        Message message = new Message();
        try {

            // here decode token and checkIfUserExist in db
            DecodeToken dtoken = decodeToken(propertyRequestModel.getToken());

            User user = userRepository.findByUsername(
                    dtoken.getSub())
                    .orElseThrow(() -> new RuntimeException("Error: user is not found."));
            // here set property to db

            System.out.println("helo ghram here1");
            Property newProperty = editProperty(propertyRequestModel);

            System.out.println("helo ghram here2");

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

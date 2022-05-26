package The.Geeks.ResmProject.service;

import java.io.UnsupportedEncodingException;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.aspectj.bridge.Message;
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

import The.Geeks.ResmProject.domain.Image;
import The.Geeks.ResmProject.domain.ImageStatus;
import The.Geeks.ResmProject.domain.Property;
import The.Geeks.ResmProject.domain.PropertyCategory;
import The.Geeks.ResmProject.domain.PropertyImage;
import The.Geeks.ResmProject.domain.PropertyStatus;
import The.Geeks.ResmProject.domain.User;
import The.Geeks.ResmProject.domain.UserImage;
import The.Geeks.ResmProject.message.ResponseFile;
import The.Geeks.ResmProject.message.ResponseMessage;
import The.Geeks.ResmProject.payload.request.PropertyRequest;
import The.Geeks.ResmProject.repo.PropertyCategoryRepo;
import The.Geeks.ResmProject.repo.PropertyImageRepo;
import The.Geeks.ResmProject.repo.PropertyRepo;
import The.Geeks.ResmProject.repo.PropertyStatusRepo;
import The.Geeks.ResmProject.repo.UserRepo;
import The.Geeks.ResmProject.service.DecodeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;

@RequiredArgsConstructor
@Component
@Service
public class PropertySreviceImp implements PropertyService {

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

        @Override
        public ResponseEntity<ResponseMessage> addProperty(
                        @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,
                        @RequestPart("propertyRequest") PropertyRequest propertyRequest)
                        throws UnsupportedEncodingException, Exception {

                String token = propertyRequest.getToken();

                DecodeToken dtoken = DecodeToken.getDecoded(token);

                System.out.println(dtoken);

                User user = userRepository.findByUsername(
                                dtoken.getSub())
                                .orElseThrow(() -> new RuntimeException("Error: user is not found."));

                Property newProperty = new Property();
                newProperty.setDateAdded(propertyRequest.getPropertyInfo().getDateAdded());
                newProperty.setDescription(propertyRequest.getPropertyInfo().getDescription());
                newProperty.setNumBathrooms(propertyRequest.getPropertyInfo().getNumBathrooms());
                newProperty.setNumRooms(propertyRequest.getPropertyInfo().getNumRooms());
                newProperty.setNumStoreys(propertyRequest.getPropertyInfo().getNumStoreys());
                newProperty.setPrice(propertyRequest.getPropertyInfo().getPrice());
                newProperty.setSpace(propertyRequest.getPropertyInfo().getSpace());
                Optional<PropertyCategory> propertyCategory = propertyCategoryRepo
                                .findById(propertyRequest.getPropertyInfo().getProperty_categoryid());
                newProperty.setPropertyCategory(propertyCategory.get());
                Optional<PropertyStatus> propertyStatus = propertyStatusRepo
                                .findById(propertyRequest.getPropertyInfo().getProperty_statusid());
                newProperty.setPropertyStatus(propertyStatus.get());

                newProperty.setUser(user);
                propertyRepo.save(newProperty);

                uploadFile(file);
                ImageStatus imageStatus = new ImageStatus();
                long x = 1;
                PropertyImage propertyImge = new PropertyImage(x, newProperty, file.getBytes(), imageStatus);
                propertyImageRepo.save(propertyImge);

                return ResponseEntity.ok().build();

        }

        @Autowired
        private FileStorageService storageService;

        public ResponseEntity<ResponseMessage> uploadFile(MultipartFile file) {
                String message = "";
                try {
                        storageService.store(file);

                        message = "Uploaded the file successfully: " + file.getOriginalFilename();
                        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
                } catch (Exception e) {
                        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
                }
        }

}

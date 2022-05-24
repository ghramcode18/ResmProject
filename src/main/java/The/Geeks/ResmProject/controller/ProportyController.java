package The.Geeks.ResmProject.controller;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import The.Geeks.ResmProject.domain.Property;
import The.Geeks.ResmProject.domain.PropertyCategory;
import The.Geeks.ResmProject.domain.PropertyStatus;
import The.Geeks.ResmProject.domain.User;
import The.Geeks.ResmProject.payload.request.PropertyRequest;
import The.Geeks.ResmProject.repo.PropertyCategoryRepo;
import The.Geeks.ResmProject.repo.PropertyRepo;
import The.Geeks.ResmProject.repo.PropertyStatusRepo;
import The.Geeks.ResmProject.repo.UserRepo;
import The.Geeks.ResmProject.service.DecodeToken;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j

public class ProportyController {
    // @Autowired
    // PasswordEncoder encoder;

    @Autowired
    UserRepo userRepository;

    @Autowired
    PropertyRepo propertyRepo;

    @Autowired
    PropertyCategoryRepo propertyCategoryRepo;

    @Autowired
    PropertyStatusRepo propertyStatusRepo;

    @PostMapping("/addProperty")
    public ResponseEntity<?> addProperty(@RequestBody PropertyRequest propertyRequest)
            throws UnsupportedEncodingException, Exception {
        // TODO method
        String token = propertyRequest.getToken();

        DecodeToken dtoken = DecodeToken.getDecoded(token);

        System.out.println(dtoken);

        User user = userRepository.findByUsername(
                dtoken.getSub())
                .orElseThrow(() -> new RuntimeException("Error: user is not found."));
        System.out.println(propertyRequest.getPropertyInfo());

        Property newProperty = new Property();
        newProperty.setDateAdded(propertyRequest.getPropertyInfo().getDateAdded());
        newProperty.setDescription(propertyRequest.getPropertyInfo().getDescription());
        newProperty.setNumBathrooms(propertyRequest.getPropertyInfo().getNumBathrooms());
        newProperty.setNumRooms(propertyRequest.getPropertyInfo().getNumRooms());
        newProperty.setNumStoreys(propertyRequest.getPropertyInfo().getNumStoreys());
        newProperty.setPrice(propertyRequest.getPropertyInfo().getPrice());
        newProperty.setSpace(propertyRequest.getPropertyInfo().getSpace());
        Optional<PropertyCategory> propertyCategory=
        propertyCategoryRepo.findById(propertyRequest.getPropertyInfo().getProperty_categoryid());
        newProperty.setPropertyCategory(propertyCategory.get());
        Optional <PropertyStatus>propertyStatus
        =propertyStatusRepo.findById(propertyRequest.getPropertyInfo().getProperty_statusid());
         newProperty.setPropertyStatus(propertyStatus.get());

         
         newProperty.setUser(user);
        propertyRepo.save(newProperty);

        return ResponseEntity.ok()
                .body(newProperty);

    }
}

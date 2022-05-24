package The.Geeks.ResmProject.controller;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import The.Geeks.ResmProject.service.PropertySreviceImp;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j

public class ProportyController {
    // @Autowired
    // PasswordEncoder encoder;

   
    @Autowired
    PropertySreviceImp proertyserviceImp;

    @PostMapping("/addProperty")
    public Object addProperty(@RequestBody PropertyRequest propertyRequest)
            throws UnsupportedEncodingException, Exception {
        try {
            return proertyserviceImp.addProperty(propertyRequest);

        } catch (Exception e) {
         return e.getMessage();

        }
    }
}

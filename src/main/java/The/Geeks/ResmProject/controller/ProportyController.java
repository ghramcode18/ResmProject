package The.Geeks.ResmProject.controller;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import The.Geeks.ResmProject.domain.Image;
import The.Geeks.ResmProject.message.ResponseFile;
import The.Geeks.ResmProject.message.ResponseMessage;

@RestController
@RequestMapping("/api/v1")
@Slf4j

public class ProportyController {
    // @Autowired
    // PasswordEncoder encoder;

    /*
     * @RequestMapping(value = "/executesampleservice", method = RequestMethod.POST,
     * consumes = {"multipart/form-data"})
     * 
     * @ResponseBody
     * public boolean executeSampleService(
     * 
     * @RequestPart("properties") @Valid ConnectionProperties properties,
     * 
     * @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file) {
     * return projectService.executeSampleService(properties, file);
     */
    @Autowired
    PropertySreviceImp proertyserviceImp;

    @RequestMapping(value = "/addProperty", method = RequestMethod.POST, consumes = { "multipart/form-data" })

    @ResponseBody
    public Object addProperty(
            @RequestParam("files") MultipartFile[] files,
            @RequestPart("propertyRequest") @Valid PropertyRequest propertyRequest)
            throws UnsupportedEncodingException, Exception {
        try {
            return proertyserviceImp.addProperty(files, propertyRequest);

        } catch (Exception e) {
            return e.getMessage();

        }
    }

}

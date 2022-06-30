package The.Geeks.ResmProject.controller;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import The.Geeks.ResmProject.payload.request.PropertyRequest;
import The.Geeks.ResmProject.service.PropertyServieceImp;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
@Slf4j

public class ProportyController {
   

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
    PropertyServieceImp proertyserviceImp;

    @RequestMapping(value = "/addProperty", method = RequestMethod.POST, consumes = { "multipart/form-data" })

    @ResponseBody
    public Object addProperty(
            @RequestParam("files") MultipartFile[] files,
            @RequestPart("propertyRequest") @Valid PropertyRequest propertyRequest)
            throws UnsupportedEncodingException, Exception {

        return proertyserviceImp.addProperty(files, propertyRequest);

    }

}

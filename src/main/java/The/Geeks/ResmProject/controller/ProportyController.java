package The.Geeks.ResmProject.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import The.Geeks.ResmProject.domain.Property;
import The.Geeks.ResmProject.model.propertyRequestModel;
import The.Geeks.ResmProject.payload.request.SearchPriceRequest;
import The.Geeks.ResmProject.payload.request.SearchSpaceRequest;
import The.Geeks.ResmProject.payload.request.SearchUserRequest;
import The.Geeks.ResmProject.payload.response.SearchResponce;
import The.Geeks.ResmProject.payload.request.PropertyRequest;
import The.Geeks.ResmProject.payload.request.SearchNumBathroomsRequest;
import The.Geeks.ResmProject.payload.request.SearchNumRoomsRequest;
import The.Geeks.ResmProject.payload.request.SearchNumStoreysRequest;
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

    @RequestMapping(value = "/editProperty", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @ResponseBody
    public Object editProperty(
            @RequestPart(value = "files", required = false) MultipartFile[] files,
            @RequestPart("propertyRequestModel") @Valid propertyRequestModel propertyRequestModel)
            throws UnsupportedEncodingException, Exception {

        return proertyserviceImp.editProperty(files, propertyRequestModel);

    }

    @PostMapping("/searchPrice")
    public SearchResponce searchPrice(@RequestBody SearchPriceRequest searchPriceRequest)
            throws UnsupportedEncodingException {
        return proertyserviceImp.searchPrice(searchPriceRequest);
    }

    @PostMapping("/searchSpace")
    public SearchResponce searchSpace(@RequestBody SearchSpaceRequest searchSpaceRequest)
            throws UnsupportedEncodingException {
        return proertyserviceImp.searchSpace(searchSpaceRequest);
    }

    @PostMapping("/searchNumRooms")
    public SearchResponce searchNumRooms(@RequestBody SearchNumRoomsRequest searchNumRoomsRequest)
            throws UnsupportedEncodingException {
        return proertyserviceImp.searchNumRooms(searchNumRoomsRequest);
    }

    @PostMapping("/searchNumBathrooms")
    public SearchResponce searchNumBathrooms(@RequestBody SearchNumBathroomsRequest searchNumBathroomsRequest)
            throws UnsupportedEncodingException {
        return proertyserviceImp.searchNumBathrooms(searchNumBathroomsRequest);
    }

    @PostMapping("/searchNumStoreys")
    public SearchResponce searchNumStoreys(@RequestBody SearchNumStoreysRequest searchnumStoreysRequest)
            throws UnsupportedEncodingException {
        return proertyserviceImp.searchNumStoreys(searchnumStoreysRequest);
    }

    @PostMapping("/searchPropertyCategory")
    public List<Property> searchPropertyCategory(@RequestBody Integer Category) {
        return proertyserviceImp.searchPropertyCategory(Category);
    }

    @PostMapping("/SearchUserRequest")
    public SearchResponce searchUser(@RequestBody SearchUserRequest searchUserRequest)
            throws UnsupportedEncodingException {
        return proertyserviceImp.searchUser(searchUserRequest);

    }
}
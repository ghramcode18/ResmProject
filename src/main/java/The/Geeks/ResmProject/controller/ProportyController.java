package The.Geeks.ResmProject.controller;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import The.Geeks.ResmProject.message.ResponseMessage;
import The.Geeks.ResmProject.model.propertyRequestModel;
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
import The.Geeks.ResmProject.payload.request.DeletePropertyFromFvaoriteLsitRequest;
import The.Geeks.ResmProject.payload.request.DeletePropertyRequest;
import The.Geeks.ResmProject.payload.request.HomePageReqest;
import The.Geeks.ResmProject.payload.request.PropertyRequest;
import The.Geeks.ResmProject.service.PropertyServieceImp;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
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
    public SearchResponce searchPropertyCategory(
            @RequestBody SearchPropertyCategoryRequest searchPropertyCategoryRequest)
            throws UnsupportedEncodingException {    
                    return proertyserviceImp.searchPropertyCategory(searchPropertyCategoryRequest);
    }

    @PostMapping("/SearchUserRequest")
    public SearchResponce searchUser(@RequestBody SearchUserRequest searchUserRequest)
            throws UnsupportedEncodingException {
        return proertyserviceImp.searchUser(searchUserRequest);

    }
    @PostMapping("/searchDateAdded")
     public SearchResponce searchDateAdded(
            @RequestBody SearchDateAddedRequest searchDateAddedRequest)
            throws UnsupportedEncodingException {
                    return proertyserviceImp.searchDateAdded(searchDateAddedRequest);

        }
        
        @PostMapping("/deleteProperty")
        public ResponseEntity<ResponseMessage> deleteProperty(@RequestBody DeletePropertyRequest deletePropertyRequest)
                throws UnsupportedEncodingException {
            return proertyserviceImp.deleteProperty(deletePropertyRequest);

        }

        @PostMapping("/deletePropertyFromFavaoriteList")
        public ResponseEntity<ResponseMessage> deletePropertyFromFavaoriteList(
            @RequestBody DeletePropertyFromFvaoriteLsitRequest deletePropertyRequest)
            throws UnsupportedEncodingException{

                return proertyserviceImp.deletePropertyFromFavaoriteList(deletePropertyRequest);

        }

        @PostMapping("/viewHomePage")
        public ViewHomePage viewHomePage(
            @RequestBody HomePageReqest homePageReqest) 
            throws UnsupportedEncodingException {

        return proertyserviceImp.viewHomePage(homePageReqest);

    }

        

}
package The.Geeks.ResmProject.controller;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import The.Geeks.ResmProject.payload.request.AddPropertyToFavoriteListRequest;
import The.Geeks.ResmProject.payload.request.ProfileEditRequest;

import The.Geeks.ResmProject.payload.request.SingUpRequest;
import The.Geeks.ResmProject.service.UserServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    
    @Autowired
    private UserServiceImpl userServiceImp;

    @RequestMapping(value = "/sigup", method = RequestMethod.POST)
    public Object singUp(
            @RequestBody  SingUpRequest singUpRequest) throws Exception {
        return userServiceImp.singUp(singUpRequest);

    }
    
    @GetMapping(value = "/verify/{id}")
    public String verifyUser(@PathVariable Long id) throws Exception {
        return userServiceImp.verify(id);
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @ResponseBody
    public Object editProperty(
            @RequestPart(value = "files",required = false) MultipartFile[] files ,
            @RequestPart("profileEditRequest") @Valid ProfileEditRequest profileEditRequest)
            throws UnsupportedEncodingException, Exception {
                
        return userServiceImp.profileEdit(files, profileEditRequest);

    }

    @RequestMapping(value = "/viewProfile", method = RequestMethod.POST)
    public Object viewHomePage(
            @RequestBody String token) throws Exception {

        return userServiceImp.viewProfile(token);

    }


    @RequestMapping(value = "/addPropertyToFavoriteList", method = RequestMethod.POST)
    public Object addPropertyToFavoriteList(
            @RequestBody AddPropertyToFavoriteListRequest addPropertyToFavoriteListRequest) throws Exception {

        return  userServiceImp.addPropertyToFavoriteList(addPropertyToFavoriteListRequest);
       
    }

    @RequestMapping(value = "/viewPropertyFavoriteList", method = RequestMethod.POST)
    public Object viewPropertyFavoriteList(
            @RequestBody String token) throws Exception {

        return userServiceImp.viewPropertyFavoriteList(token);

    }
    
    

}

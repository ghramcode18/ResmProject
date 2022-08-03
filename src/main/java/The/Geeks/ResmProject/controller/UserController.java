package The.Geeks.ResmProject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import The.Geeks.ResmProject.payload.request.AddPropertyToFavoriteListRequest;
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

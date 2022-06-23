package The.Geeks.ResmProject.controller;

import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import The.Geeks.ResmProject.message.ResponseMessage;

import The.Geeks.ResmProject.domain.User;
import The.Geeks.ResmProject.exception.UserException;
import The.Geeks.ResmProject.model.UserModel;
import The.Geeks.ResmProject.payload.request.AddPropertyToFavoriteListRequest;
import The.Geeks.ResmProject.service.Message;
import The.Geeks.ResmProject.service.UserService;
import The.Geeks.ResmProject.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImp;
    private UserException userException;

    @PostMapping("/user/signup")
    public ResponseEntity<?> singUp(@RequestBody User user) throws Exception {
        userServiceImp.singUp(user);
        return ResponseEntity.ok().build();
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

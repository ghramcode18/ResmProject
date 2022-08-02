package The.Geeks.ResmProject.api;

import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import The.Geeks.ResmProject.domain.User;
import The.Geeks.ResmProject.exception.UserException;
import The.Geeks.ResmProject.model.UserModel;
import The.Geeks.ResmProject.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    private UserException userException;

    // @PostMapping("/auth/v1")
    // public Map<String, String> singUp(@RequestBody User user) throws UserException {
    //     userService.singUp(user);
    //     return userException.exception("Done..");
    // }

    // @PostMapping("/auth/v1")
    // public ResponseEntity<?> singUp(@RequestBody User user) throws Exception {
    //     userService.singUp(user);
    //     return ResponseEntity.ok().build();
    // }

    @PostMapping("/auth/v1")
    public ResponseEntity<?> register(@RequestBody UserModel userModel) throws Exception{
        userService.singUp(userModel);
        return ResponseEntity.ok().build();
    }
}

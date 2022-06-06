package The.Geeks.ResmProject.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;

import The.Geeks.ResmProject.domain.User;
import The.Geeks.ResmProject.message.ResponseMessage;
import The.Geeks.ResmProject.model.UserModel;
import The.Geeks.ResmProject.payload.request.AddPropertyToFavoriteListRequest;
import The.Geeks.ResmProject.payload.request.PropertyRequest;

@Service
public interface UserService {
    void singUp(User user) throws Exception;

    boolean checkIfUserExist(String email);

    ResponseEntity<ResponseMessage> addPropertyToFavoriteList(
            @RequestPart("propertyRequest") AddPropertyToFavoriteListRequest addPropertyToFavoriteListRequest, Integer integer);
}

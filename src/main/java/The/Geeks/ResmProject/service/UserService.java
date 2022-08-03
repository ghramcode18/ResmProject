package The.Geeks.ResmProject.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;

import The.Geeks.ResmProject.message.ResponseMessage;
import The.Geeks.ResmProject.payload.request.AddPropertyToFavoriteListRequest;
import The.Geeks.ResmProject.payload.request.SingUpRequest;
import The.Geeks.ResmProject.payload.response.ViewPropertyFavoriteListResponse;

@Service
public interface UserService {
public ResponseEntity<ResponseMessage> singUp(
            @RequestBody SingUpRequest singUpRequest) throws Exception ;

     ResponseEntity<ResponseMessage> addPropertyToFavoriteList(
            @RequestPart("propertyRequest") AddPropertyToFavoriteListRequest addPropertyToFavoriteListRequest);

    ViewPropertyFavoriteListResponse viewPropertyFavoriteList( String token);
}

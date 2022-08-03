package The.Geeks.ResmProject.service;

import java.io.UnsupportedEncodingException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;

import The.Geeks.ResmProject.message.ResponseMessage;
import The.Geeks.ResmProject.payload.request.AddPropertyToFavoriteListRequest;
import The.Geeks.ResmProject.payload.request.singUpRequest;
import The.Geeks.ResmProject.payload.response.ViewPropertyFavoriteListResponse;

@Service
public interface UserService {

     ResponseEntity<ResponseMessage> singUp(
                        @RequestBody singUpRequest singUpRequest)
                        throws Exception;

     ResponseEntity<ResponseMessage> addPropertyToFavoriteList(
            @RequestPart("propertyRequest") AddPropertyToFavoriteListRequest addPropertyToFavoriteListRequest);

    ViewPropertyFavoriteListResponse viewPropertyFavoriteList( String token);
}

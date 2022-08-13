package The.Geeks.ResmProject.service;

import java.io.UnsupportedEncodingException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import The.Geeks.ResmProject.domain.UserFav;
import The.Geeks.ResmProject.message.ResponseMessage;
import The.Geeks.ResmProject.payload.request.AddPropertyToFavoriteListRequest;
import The.Geeks.ResmProject.payload.request.ProfileEditRequest;
import The.Geeks.ResmProject.payload.request.SingUpRequest;
import The.Geeks.ResmProject.payload.response.ViewAllUser;
import The.Geeks.ResmProject.payload.response.ViewProfile;
import The.Geeks.ResmProject.payload.response.ViewPropertyFavoriteListResponse;
import java.util.List;

@Service
public interface UserService {

    ResponseEntity<ResponseMessage> singUp(
            @RequestBody SingUpRequest singUpRequest)
            throws Exception;

    ResponseEntity<ResponseMessage> profileEdit(
            @RequestParam("files") MultipartFile[] files,
            @RequestPart("profileEditRequest") ProfileEditRequest profileEditRequest)
            throws UnsupportedEncodingException, Exception;

    ViewProfile viewProfile(String token) throws UnsupportedEncodingException, Exception;

    ViewAllUser viewAllUsers(String token)throws UnsupportedEncodingException, Exception;

    public ResponseEntity<ResponseMessage> addPropertyToFavoriteList(
            AddPropertyToFavoriteListRequest addPropertyToFavoriteListRequest);

    ViewPropertyFavoriteListResponse viewPropertyFavoriteList(String token);

    List<UserFav> findPaginated(int pageNo, int pageSize);

}

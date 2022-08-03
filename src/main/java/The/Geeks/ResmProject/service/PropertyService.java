package The.Geeks.ResmProject.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import The.Geeks.ResmProject.domain.Property;
import The.Geeks.ResmProject.message.ResponseMessage;
import The.Geeks.ResmProject.model.propertyRequestModel;
import The.Geeks.ResmProject.payload.request.SearchPriceRequest;
import The.Geeks.ResmProject.payload.request.SearchSpaceRequest;
import The.Geeks.ResmProject.payload.request.SearchUserRequest;
import The.Geeks.ResmProject.payload.request.PropertyRequest;
import The.Geeks.ResmProject.payload.request.SearchNumBathroomsRequest;
import The.Geeks.ResmProject.payload.request.SearchNumRoomsRequest;
import The.Geeks.ResmProject.payload.request.SearchNumStoreysRequest;
import The.Geeks.ResmProject.payload.response.SearchResponce;

@Service
public interface PropertyService {
        public ResponseEntity<ResponseMessage> addProperty(
                        @RequestParam("files") MultipartFile[] files,
                        @RequestPart("propertyRequest") PropertyRequest propertyRequest)
                        throws UnsupportedEncodingException, Exception;

        public ResponseEntity<ResponseMessage> editProperty(

                        @RequestParam("files") MultipartFile[] files,
                        @RequestPart("propertyRequestModel") propertyRequestModel propertyRequestModel)
                        throws UnsupportedEncodingException, Exception;

        public SearchResponce searchPrice(@RequestBody SearchPriceRequest searchPriceRequest)
                        throws UnsupportedEncodingException;

        public SearchResponce searchSpace(@RequestBody SearchSpaceRequest searchSpaceRequest)
                        throws UnsupportedEncodingException;

        public SearchResponce searchNumRooms(@RequestBody SearchNumRoomsRequest searchNumRoomsRequest)
                        throws UnsupportedEncodingException;

        public SearchResponce searchNumStoreys(@RequestBody SearchNumStoreysRequest searchnumStoreysRequest)
                        throws UnsupportedEncodingException;

        public SearchResponce searchNumBathrooms(@RequestBody SearchNumBathroomsRequest searchNumBathroomsRequest)
                        throws UnsupportedEncodingException;

        public SearchResponce searchUser(@RequestBody SearchUserRequest searchUserRequest)
                        throws UnsupportedEncodingException;

        public List<Property> searchDateAdded(String dateAdded);

        public List<Property> searchPropertyCategory(Integer propertyCategory);


}

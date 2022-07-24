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
import The.Geeks.ResmProject.payload.request.PropertyRequest;

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

        public List<Property> searchPrice(Float price);

        public List<Property> searchSpace(Float space);

        public List<Property> searchNumRooms(Integer numRooms);

        public List<Property> searchNumStoreys(Integer numStoreys);

        public List<Property> searchNumBathrooms(Integer numBathrooms);

        public List<Property> searchDateAdded(String dateAdded);

        public List<Property> searchPropertyCategory(Integer propertyCategory);

        public List<Property> searchUser(String userName);

}

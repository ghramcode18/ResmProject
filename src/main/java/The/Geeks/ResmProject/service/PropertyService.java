package The.Geeks.ResmProject.service;

import java.io.UnsupportedEncodingException;

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
import The.Geeks.ResmProject.payload.request.PropertyRequest;
@Service
public interface PropertyService {
    public ResponseEntity<ResponseMessage> addProperty(
            @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,@RequestPart ("propertyRequest")PropertyRequest propertyRequest) throws UnsupportedEncodingException, Exception;
}

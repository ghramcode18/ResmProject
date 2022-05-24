package The.Geeks.ResmProject.service;

import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import The.Geeks.ResmProject.domain.Property;
import The.Geeks.ResmProject.payload.request.PropertyRequest;
@Service
public interface PropertyService {
    public HttpStatus addProperty(@RequestBody PropertyRequest propertyRequest) throws UnsupportedEncodingException, Exception;
}

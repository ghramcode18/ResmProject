package The.Geeks.ResmProject.model;

import The.Geeks.ResmProject.payload.request.newPropertyInfo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import org.springframework.web.multipart.MultipartFile;

import The.Geeks.ResmProject.domain.PropertyCategory;
import The.Geeks.ResmProject.domain.PropertyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Builder
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class propertyRequestModel {
    String token;
    String propertyId;
    public newPropertyInfo newPropertyInfo;
}

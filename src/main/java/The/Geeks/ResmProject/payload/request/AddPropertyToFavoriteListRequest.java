package The.Geeks.ResmProject.payload.request;

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

public class AddPropertyToFavoriteListRequest {
    private String token;
    private String propertyId;

    

}

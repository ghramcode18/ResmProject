package The.Geeks.ResmProject.payload.request.search;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import org.springframework.web.multipart.MultipartFile;

import The.Geeks.ResmProject.domain.PropertyCategory;
import The.Geeks.ResmProject.domain.PropertyStatus;
import The.Geeks.ResmProject.message.ResponseMessage;
import The.Geeks.ResmProject.payload.response.address;
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
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class SearchNumBathroomsRequest {
    SearchNumBathroomsRequestInfo  searchNumBathroomsRequestInfo = new SearchNumBathroomsRequestInfo();
}

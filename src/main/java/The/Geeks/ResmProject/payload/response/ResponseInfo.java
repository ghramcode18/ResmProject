package The.Geeks.ResmProject.payload.response;

import The.Geeks.ResmProject.message.ResponseMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import The.Geeks.ResmProject.domain.Property;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Setter
@Getter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

@Builder
public class ResponseInfo {

    List<Property> propertiesList = new ArrayList<Property>();
}
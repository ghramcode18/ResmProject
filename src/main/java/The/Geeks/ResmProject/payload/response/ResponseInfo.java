package The.Geeks.ResmProject.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    List<PropertyView> propertiesList = new ArrayList<PropertyView>();
}
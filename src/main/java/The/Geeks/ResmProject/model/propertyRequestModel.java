package The.Geeks.ResmProject.model;

import The.Geeks.ResmProject.payload.request.newPropertyInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

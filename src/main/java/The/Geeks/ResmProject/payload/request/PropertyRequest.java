package The.Geeks.ResmProject.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import The.Geeks.ResmProject.domain.Property;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties

@Setter
@Getter
@Builder
public class PropertyRequest {
      private String token;
      private PropertyInfo propertyInfo;
}

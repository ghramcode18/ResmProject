package The.Geeks.ResmProject.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class PropertyRequest {
      private String token;
      private PropertyInfo propertyInfo;
}

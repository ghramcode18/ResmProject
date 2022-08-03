package The.Geeks.ResmProject.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Builder
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class singUpInfoRequest {
    private String username;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String password;
    address address;
}

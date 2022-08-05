package The.Geeks.ResmProject.payload.request;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ProfileEditInfoRequest {
    private String username;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateAdded;
}

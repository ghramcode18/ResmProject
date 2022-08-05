package The.Geeks.ResmProject.payload.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ProfileInfo {
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    address address;
    List<ProfilePropertyView> propertiesList = new ArrayList<ProfilePropertyView>();
}

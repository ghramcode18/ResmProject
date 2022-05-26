package The.Geeks.ResmProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @Data
public class UserModel {
    String username;
    String hashedPassword;
    String firstName;
    String lastName;
    Float lattitude;
    Float longitutde;
    String regionname;
    String cityname;
    String countryname;
}

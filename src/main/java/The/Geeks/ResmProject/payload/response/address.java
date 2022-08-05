package The.Geeks.ResmProject.payload.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class address {
    Float longitutde;
    Float lattitude;
    String addressDescription;
    public region region;
}

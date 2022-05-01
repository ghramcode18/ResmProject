package The.Geeks.ResmProject.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@With
public class CategoryDto {
    private Integer id;

    // value of name: Villa, apartment, agricultural_land, shop, office, lab,
    // industrial_hangar, studio;
 
    private String name;

    private ProprtyDto proprty;

}

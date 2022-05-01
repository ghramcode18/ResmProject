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
public class proprty_typeDto {
    private Integer id;
    
    // value of type: rent, sell, invest, mortgage;
    private String type;

    private ProprtyDto proprty;

}

package The.Geeks.ResmProject.Dto;

import java.util.Date;

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
public class ImageDto {
    
    private Integer id;
    private String name;

    private String image_url;

    private Date date_in;

    private ProprtyDto proprty;

    private UserDto user;
}

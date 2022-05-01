package The.Geeks.ResmProject.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;

import java.util.Date;
import java.util.List;

import The.Geeks.ResmProject.Dto.ImageDto;
import The.Geeks.ResmProject.Dto.proprty_typeDto;
import The.Geeks.ResmProject.Dto.CategoryDto;

import The.Geeks.ResmProject.Dto.UserDto;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@With
public class ProprtyDto {
    

    private Integer id;
    private String proprty_name;
    private double price;
    private double space;
    private Integer num_rooms;
    private Integer num_bathrooms;
    private String description;
    private boolean available;
    private String cladding_type;
    private List<ImageDto> image_url;
    private Date date_in;
    private List<proprty_typeDto> proprty_type;
    private List<CategoryDto> categoryDto;
    private UserDto user;
   
   
   

   
 

}

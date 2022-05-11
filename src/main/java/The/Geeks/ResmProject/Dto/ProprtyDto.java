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
import The.Geeks.ResmProject.Dto.Proprty_typeDto;
import The.Geeks.ResmProject.Dto.CategoryDto;

import The.Geeks.ResmProject.Dto.UserDto;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
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
    private Proprty_typeDto proprty_type;

    public ProprtyDto id(Integer id) {
        setId(id);
        return this;
    }

    public ProprtyDto proprty_name(String proprty_name) {
        setProprty_name(proprty_name);
        return this;
    }

    public ProprtyDto price(double price) {
        setPrice(price);
        return this;
    }

    public ProprtyDto space(double space) {
        setSpace(space);
        return this;
    }

    public ProprtyDto num_rooms(Integer num_rooms) {
        setNum_rooms(num_rooms);
        return this;
    }

    public ProprtyDto num_bathrooms(Integer num_bathrooms) {
        setNum_bathrooms(num_bathrooms);
        return this;
    }

    public ProprtyDto description(String description) {
        setDescription(description);
        return this;
    }

    public ProprtyDto available(boolean available) {
        setAvailable(available);
        return this;
    }

    public ProprtyDto cladding_type(String cladding_type) {
        setCladding_type(cladding_type);
        return this;
    }

    public ProprtyDto image_url(List<ImageDto> image_url) {
        setImage_url(image_url);
        return this;
    }

    public ProprtyDto date_in(Date date_in) {
        setDate_in(date_in);
        return this;
    }

    public ProprtyDto proprty_type(Proprty_typeDto proprty_type) {
        setProprty_type(proprty_type);
        return this;
    }

    public ProprtyDto categoryDto(List<CategoryDto> categoryDto) {
        setCategoryDto(categoryDto);
        return this;
    }

    public ProprtyDto user(UserDto user) {
        setUser(user);
        return this;
    }
    private List<CategoryDto> categoryDto;
    private UserDto user;
   
   
   

   
 

}

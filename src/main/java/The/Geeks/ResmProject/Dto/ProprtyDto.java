package The.Geeks.ResmProject.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

import The.Geeks.ResmProject.Entities.User;

@Setter
@Getter
@RequiredArgsConstructor

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
    private String image_url;
    private Date date_in;
    private proprty_type type;
    private Category category;
    private User user;

   
 

}

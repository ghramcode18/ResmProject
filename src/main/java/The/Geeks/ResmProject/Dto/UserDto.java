package The.Geeks.ResmProject.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;

import java.util.Date;
import java.util.List;

import The.Geeks.ResmProject.Entities.Image;
import The.Geeks.ResmProject.Dto.Proprty_typeDto;
import The.Geeks.ResmProject.Dto.CategoryDto;

import The.Geeks.ResmProject.Entities.User;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@With
public class UserDto {
    private Integer id ;
    private String full_name;

}

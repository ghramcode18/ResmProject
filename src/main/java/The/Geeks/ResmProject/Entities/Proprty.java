package The.Geeks.ResmProject.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name = "proprties")

public class Proprty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = true)
    private String proprty_name;

    @Column(nullable = true)
    private double price;

    @Column(nullable = true)
    private double space;

    @Column(nullable = true)
    private Integer num_rooms;

    @Column(nullable = true)
    private Integer num_bathrooms;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private boolean available;

    @Column(nullable = true)
    private String cladding_type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_in;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "proprties_id")
    private List<Image> images = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "proprties_id")
    private List<Category> categories = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "proprties_id")
    private List<proprty_type> proprty_type = new ArrayList<>();

    public Proprty id(Integer id) {
        setId(id);
        return this;
    }

    public Proprty proprty_name(String proprty_name) {
        setProprty_name(proprty_name);
        return this;
    }

    public Proprty price(double price) {
        setPrice(price);
        return this;
    }

    public Proprty space(double space) {
        setSpace(space);
        return this;
    }

    public Proprty num_rooms(Integer num_rooms) {
        setNum_rooms(num_rooms);
        return this;
    }

    public Proprty num_bathrooms(Integer num_bathrooms) {
        setNum_bathrooms(num_bathrooms);
        return this;
    }

    public Proprty description(String description) {
        setDescription(description);
        return this;
    }

    public Proprty available(boolean available) {
        setAvailable(available);
        return this;
    }

    public Proprty cladding_type(String cladding_type) {
        setCladding_type(cladding_type);
        return this;
    }

    public Proprty date_in(Date date_in) {
        setDate_in(date_in);
        return this;
    }

    public Proprty user(User user) {
        setUser(user);
        return this;
    }

    public Proprty images(List<Image> images) {
        setImages(images);
        return this;
    }

    public Proprty categories(List<Category> categories) {
        setCategories(categories);
        return this;
    }

    public Proprty proprty_type(List<proprty_type> proprty_type) {
        setProprty_type(proprty_type);
        return this;
    }

}

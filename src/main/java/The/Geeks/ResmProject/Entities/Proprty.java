package The.Geeks.ResmProject.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
@Data
@Setter
@Getter
@RequiredArgsConstructor

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
}

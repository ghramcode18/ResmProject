package The.Geeks.ResmProject.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import java.util.ArrayList;
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
@NoArgsConstructor

@Table(name = "regions")

public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private Long latitude;

    @Column(nullable = true)
    private Long longitude;

    @ManyToOne
    @JoinColumn(name = "cities_id")
    private City city;

    @OneToMany
    @JoinColumn(name = "regions_id")
    private List<Address> addresses = new ArrayList<>();

}

package The.Geeks.ResmProject.Entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@Data
@Setter
@Getter
@RequiredArgsConstructor

@Table(name = "countries")

public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String internationalNumber;

    @OneToMany
    @JoinColumn(name = "countries_id")
    private List<City> city = new ArrayList<>();

}

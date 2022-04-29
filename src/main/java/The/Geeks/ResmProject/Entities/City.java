package The.Geeks.ResmProject.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.CascadeType;
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

@Table(name = "sites")

public class City {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String name;
    private String national_number;

   
    @ManyToOne
    @JoinColumn(name = "Countries_id", nullable = false)
    private Country country;

    // @OneToMany(mappedBy = "sites")
    // private List<Region> region = new ArrayList<>();

}

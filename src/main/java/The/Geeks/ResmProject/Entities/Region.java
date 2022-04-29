package The.Geeks.ResmProject.Entities;

import java.util.Date;
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

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor

@Table(name = "Regions")

public class Region {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String name;
    private Long latitude;
    private Long longitude;

    // @ManyToOne(optional = true, targetEntity = Sity.class)
    // private Sity sity;

}

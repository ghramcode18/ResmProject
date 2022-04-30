package The.Geeks.ResmProject.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor

@Table(name = "addresses")

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = true)
    private String city_name;
    
    @Column(nullable = true)
    private String region_name;

    @Column(nullable = true)
    private Long latitude;

    @Column(nullable = true)
    private Long longitude;

    @ManyToOne
    @JoinColumn(name = "regions_id")
    private Region region;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}

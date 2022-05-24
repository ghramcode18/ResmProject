package The.Geeks.ResmProject.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "addresses") @NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long addressId;
    String addressDescription;
    Float longitutde;
    Float lattitude;
    @ManyToOne
    @JoinColumn(name = "regionID", nullable = false)
    private Region region;
}

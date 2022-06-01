package The.Geeks.ResmProject.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "addresses") @NoArgsConstructor @AllArgsConstructor @Setter @Getter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Address {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long addressId;
    String addressDescription;
    Float longitutde;
    Float lattitude;
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "regionID", nullable = true)
    private Region region;
}

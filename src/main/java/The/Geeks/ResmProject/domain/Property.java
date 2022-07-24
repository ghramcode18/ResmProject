package The.Geeks.ResmProject.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.core.JsonParser;

@Entity
@Table(name = "properties")
@Data
@Setter
@Getter
@RequiredArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PropertyId")
    Long propertyId;
    String description;
    @JsonProperty("numBathrooms")
    Integer numBathrooms;

    @JsonProperty("numStoreys")
    Integer numStoreys;

    @JsonProperty("numRooms")
    Integer numRooms;

    @JsonProperty("space")
    Float space;

    @JsonProperty("price")
    Float price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date dateAdded;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "userID", nullable = true)
    private User user;

    @JsonIgnore
    @JsonProperty("propertyCategory")

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "propertyCategoryID", nullable = true)
    private PropertyCategory propertyCategory;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "propertyStatusID", nullable = true)
    private PropertyStatus propertyStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserFav> PropertyUserFavList;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "addressID", nullable = true)
    private Address address;

    
    public Property(Long propertyId, String description, Integer numBathrooms, Integer numStoreys, Integer numRooms,
            Float space, Float price, Date dateAdded, User user, PropertyCategory propertyCategory,
            List<UserFav> PropertyUserFavList) {
        this.propertyId = propertyId;
        this.description = description;
        this.numBathrooms = numBathrooms;
        this.numStoreys = numStoreys;
        this.numRooms = numRooms;
        this.space = space;
        this.price = price;
        this.dateAdded = dateAdded;
        this.user = user;
        this.propertyCategory = propertyCategory;
        this.PropertyUserFavList = PropertyUserFavList;
    }

}

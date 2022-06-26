package The.Geeks.ResmProject.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "properties")
@Data
@Setter
@Getter
@Builder
@RequiredArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long propertyId;
    String description;
    Integer numBathrooms;
    Integer numStoreys;
    Integer numRooms;
    Float space;
    Float price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date dateAdded;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "userID", nullable = true)
    private User user;

    @JsonIgnore
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

    public Property(Long propertyId, String description, Integer numBathrooms, Integer numStoreys, Integer numRooms,
            Float space, Float price, Date dateAdded, User user, PropertyCategory propertyCategory,
            PropertyStatus propertyStatus, List<UserFav> PropertyUserFavList) {
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
        this.propertyStatus = propertyStatus;
        this.PropertyUserFavList = PropertyUserFavList;
    }

}

package The.Geeks.ResmProject.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder

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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "propertyCategoryID", nullable = true)
    private PropertyCategory propertyCategory;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "propertyStatusID", nullable = true)
    private PropertyStatus propertyStatus;
}

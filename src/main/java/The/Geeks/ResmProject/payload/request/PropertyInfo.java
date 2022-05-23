package The.Geeks.ResmProject.payload.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import The.Geeks.ResmProject.domain.PropertyCategory;
import The.Geeks.ResmProject.domain.PropertyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PropertyInfo {
    private Long propertyId;
    private String description;
    private Integer numBathrooms;
    private Integer numStoreys;
    private Integer numRooms;
    private Float space;
    private Float price;
    private Date dateAdded;
    private Long property_categoryid;
    private String propertyStatus;

}

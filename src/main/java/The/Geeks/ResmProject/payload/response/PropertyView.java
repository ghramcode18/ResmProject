package The.Geeks.ResmProject.payload.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PropertyView {

    Long propertyId;
    String description;
    Integer numBathrooms;
    Integer numStoreys;
    Integer numRooms;
    Float space;
    Float price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date dateAdded;
    String category;
    List<String> imagesUrlList = new ArrayList<String>();
    address address;

}

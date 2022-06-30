package The.Geeks.ResmProject.payload.response;

import The.Geeks.ResmProject.message.ResponseMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import The.Geeks.ResmProject.domain.Address;
import The.Geeks.ResmProject.domain.Property;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    address address;
    List<String> imagesUrlList = new ArrayList<String>();

}

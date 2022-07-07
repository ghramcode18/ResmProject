package The.Geeks.ResmProject.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity  @Data  @AllArgsConstructor @Setter @Getter
@Table(name = "propertiesImage", schema = "targetSchemaName")

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class PropertyImage {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long propertyImageId;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "propertyId" , nullable = true)
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "imageid" , nullable = true)
    private  Image image ;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "imageStatusID" , nullable = true)
    private ImageStatus imageStatus;


    public PropertyImage() {
    }

    

    public Long getPropertyImageId() {
        return this.propertyImageId;
    }

    public void setPropertyImageId(Long propertyImageId) {
        this.propertyImageId = propertyImageId;
    }

    public Property getProperty() {
        return this.property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageStatus getImageStatus() {
        return this.imageStatus;
    }

    public void setImageStatus(Optional <ImageStatus >imageStatus) {
        this.imageStatus = imageStatus.get();
    }

    public PropertyImage propertyImageId(Long propertyImageId) {
        setPropertyImageId(propertyImageId);
        return this;
    }

    public PropertyImage property(Property property) {
        setProperty(property);
        return this;
    }

    public PropertyImage image(Image image) {
        setImage(image);
        return this;
    }

    public PropertyImage imageStatus(Optional<ImageStatus> imageStatus) {
        setImageStatus(imageStatus);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PropertyImage)) {
            return false;
        }
        PropertyImage propertyImage = (PropertyImage) o;
        return Objects.equals(propertyImageId, propertyImage.propertyImageId) && Objects.equals(property, propertyImage.property) && Objects.equals(image, propertyImage.image) && Objects.equals(imageStatus, propertyImage.imageStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyImageId, property, image, imageStatus);
    }

    @Override
    public String toString() {
        return "{" +
            " propertyImageId='" + getPropertyImageId() + "'" +
            ", property='" + getProperty() + "'" +
            ", image='" + getImage() + "'" +
            ", imageStatus='" + getImageStatus() + "'" +
            "}";
    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        

}

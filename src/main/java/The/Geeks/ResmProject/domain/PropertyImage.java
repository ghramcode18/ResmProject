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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "propertiesImage") @Data @NoArgsConstructor @AllArgsConstructor @Setter @Getter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class PropertyImage {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long propertyImageId;
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "propertyID" , nullable = true)
    private Property property;
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "imageID" , nullable = true)
    private Image image;
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "imageStatusID" , nullable = true)
    private ImageStatus imageStatus;

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

    public void setImageStatus(ImageStatus imageStatus) {
        this.imageStatus = imageStatus;
    }

    public PropertyImage(Long i, Property newProperty, byte[] bytes, ImageStatus imageStatus2) 
    { this.propertyImageId = i;
        this.property = newProperty;
        this.image.setData(bytes);
        this.imageStatus = imageStatus2;
    }

    

}

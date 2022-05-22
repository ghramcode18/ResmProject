package The.Geeks.ResmProject.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "propertiesImage") @Data @NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class PropertyImage {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long propertyImageId;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "propertyID" , nullable = false)
    private Property property;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "imageID" , nullable = false)
    private Image image;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "imageStatusID" , nullable = false)
    private ImageStatus imageStatus;
}

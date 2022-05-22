package The.Geeks.ResmProject.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "images") @Data @NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class Image {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long imageId;
    String url;
    Date dateAdded;
    
}

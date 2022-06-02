package The.Geeks.ResmProject.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "images") @Data  @AllArgsConstructor @Setter @Getter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Image {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String url;

    
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date dateAdded;


  public Image() {
  }



  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Date getDateAdded() {
    return this.dateAdded;
  }

  public void setDateAdded(Date dateAdded) {
    this.dateAdded = dateAdded;
  }

  public Image id(String id) {
    setId(id);
    return this;
  }

  public Image url(String url) {
    setUrl(url);
    return this;
  }

  public Image dateAdded(Date dateAdded) {
    setDateAdded(dateAdded);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Image)) {
            return false;
        }
        Image image = (Image) o;
        return Objects.equals(id, image.id) && Objects.equals(url, image.url) && Objects.equals(dateAdded, image.dateAdded);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, url, dateAdded);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", url='" + getUrl() + "'" +
      ", dateAdded='" + getDateAdded() + "'" +
      "}";
  }

    }

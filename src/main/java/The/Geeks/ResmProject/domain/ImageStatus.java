package The.Geeks.ResmProject.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "imagesStatus") @Data @NoArgsConstructor @AllArgsConstructor @Setter @Getter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class ImageStatus {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long imageStatusId;
    String status;

    

    public Long getImageStatusId() {
        return this.imageStatusId;
    }

    public void setImageStatusId(Long imageStatusId) {
        this.imageStatusId = imageStatusId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ImageStatus imageStatusId(Long imageStatusId) {
        setImageStatusId(imageStatusId);
        return this;
    }

    public ImageStatus status(String status) {
        setStatus(status);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ImageStatus)) {
            return false;
        }
        ImageStatus imageStatus = (ImageStatus) o;
        return Objects.equals(imageStatusId, imageStatus.imageStatusId) && Objects.equals(status, imageStatus.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageStatusId, status);
    }

    @Override
    public String toString() {
        return "{" +
            " imageStatusId='" + getImageStatusId() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }

}

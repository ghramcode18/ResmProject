package The.Geeks.ResmProject.domain;

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


@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity @Table(name  = "roles") @Data @NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleId;
    private String roleTitle;
}

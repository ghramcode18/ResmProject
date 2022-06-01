package The.Geeks.ResmProject.domain;

import java.util.Date;

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

@Entity @Table(name = "comments") @Data @NoArgsConstructor @AllArgsConstructor @Setter @Getter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long commentId;
    String comment;
    Date dateAdded;
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "propertyID" , nullable = true)
    private Property property;
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "userID" , nullable = true)
    private User user;
}

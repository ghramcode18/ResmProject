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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "comments") @Data @NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long commentId;
    String comment;
    Date dateAdded;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "propertyID" , nullable = false)
    private Property property;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "userID" , nullable = false)
    private User user;
}

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

@Entity @Table(name = "usersImage") @Data @NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class UserImage {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long userImageId;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "userID" , nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "imageID" , nullable = false)
    private Image image;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "imageStatusID" , nullable = false)
    private ImageStatus imageStatus;
}
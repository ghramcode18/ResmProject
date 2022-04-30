package The.Geeks.ResmProject.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor

@Table(name = "comments")

public class Comment {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(columnDefinition = "TEXT", length = 43333000)
    private String text;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_in;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
}

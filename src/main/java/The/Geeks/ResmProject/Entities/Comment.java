package The.Geeks.ResmProject.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Data
@Setter
@Getter
@RequiredArgsConstructor

@Table(name = "comments")

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(columnDefinition = "TEXT", length = 43333000)
    private String text;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateIn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "tags_comments", joinColumns = @JoinColumn(name = "comments_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Tag> list_comments = new ArrayList<>();

}

package The.Geeks.ResmProject.Entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor

@Table(name = "tags")

public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = true)
    private String name;

    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "users_tags", joinColumns = @JoinColumn(name = "tags_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> list_tags;

    @ManyToMany(mappedBy = "list_comments")
    private List<Comment> comments = new ArrayList<>();

}

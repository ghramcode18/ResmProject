package The.Geeks.ResmProject.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor

@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = true)
    private String full_name;

    @Column(nullable = true)
    private String bio;

    @Column(nullable = true)
    private String email_address;

    @Column(nullable = true)
    private String phone_number;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date confirmed_date;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Account account;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Proprty> proprties = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Image> images = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Chat> chat = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "chates_id")
    private List<Message> messages = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "comments_id")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(mappedBy = "list_tags")
    private List<Tag> tags = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "notifications_id")
    private List<Notification> notification = new ArrayList<>();

}

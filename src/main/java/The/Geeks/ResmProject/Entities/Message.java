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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor

@Table(name = "messages")

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_in;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sent_date;

    @Column(nullable = true)
    private String message_sent;

    @ManyToOne
    @JoinColumn(name = "chates_id")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

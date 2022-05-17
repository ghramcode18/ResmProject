package The.Geeks.ResmProject.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@Setter
@Getter
@RequiredArgsConstructor

@Table(name = "images")

public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = true)
    private String name;

    @Column(columnDefinition = "TEXT", length = 43333000)
    private String imageUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateIn;

    @ManyToOne
    @JoinColumn(name = "proprties_id")
    private Proprty proprty;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

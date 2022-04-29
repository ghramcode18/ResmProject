package The.Geeks.ResmProject.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
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
    private String full_name;
    private String bio;
    private String email_address;
    private String phone_number; 
    private Date confirmed_date;

    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AccountEntity account;

    // @ManyToMany(fetch = FetchType.EAGER)
    // private Collection<Role> roles = new ArrayList<>();

    // @ManyToMany(mappedBy = "list_Estate")
    // private Collection<EstatesEntity> estates = new ArrayList<>();

}

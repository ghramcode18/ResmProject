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

@Entity @Table(name = "users") @Data @NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long userId;
    String username;
    String hashedPassword;
    String firstName;
    String lastName;
    String phoneNumber;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "roleID" , nullable = false)
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "addressID" , nullable = false)
    private Address address;
}

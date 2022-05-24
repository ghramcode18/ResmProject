package The.Geeks.ResmProject.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "users") @Data @NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long userId;
    @Email(message = "Username is not valid", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @NotEmpty(message = "Username cannot be empty")
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

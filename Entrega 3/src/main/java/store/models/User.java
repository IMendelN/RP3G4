package store.models;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import store.models.enums.UserRole;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
	private String password;

    @Column(nullable = false)
	private String address;

    @Column(nullable = false)
	private String email; 

    @Enumerated(EnumType.ORDINAL)
	private UserRole role;
    
    private LocalDate birthDate;

    @ManyToMany
    private Set<Platform> platform;

    @ManyToMany
    private Set<Genre> genre;

    @ManyToMany
    private Set<Game> game;

    public User() {}

    public User(String name, String password, String address, String email, UserRole role, LocalDate birthDate) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.role = role;
        this.birthDate = birthDate;
    }
}

package store.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import store.models.enums.UserRole;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Scope(value = "session")
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

    @OneToMany
    private Set<Purchase> purchases = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "user_platform",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "platform_id", referencedColumnName = "id")}
    )
    private Set<Platform> platforms = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "user_genre",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id")}
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "user_game",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")}
    )
    private Set<Game> games = new HashSet<>();

    public User() {}

    public User(String name, String password, String address, String email, UserRole role, LocalDate birthDate,
            Set<Platform> platforms, Set<Genre> genres) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.role = role;
        this.birthDate = birthDate;
        this.platforms = platforms;
        this.genres = genres;
    }

    public User(String name, String password, String address, String email, UserRole role, LocalDate birthDate,
            Set<Platform> platforms, Set<Genre> genres, Set<Game> games) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.role = role;
        this.birthDate = birthDate;
        this.platforms = platforms;
        this.genres = genres;
        this.games = games;
    }

    @Override
    public String toString() {
        return "User [address=" + address + ", birthDate=" + birthDate + ", email=" + email + ", games=" + games
                + ", genres=" + genres + ", id=" + id + ", name=" + name + ", password=" + password + ", platforms="
                + platforms + ", role=" + role + "]";
    }
}

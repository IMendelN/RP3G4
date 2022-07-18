package store.models;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String developer;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private Double price;

    @ManyToMany(mappedBy = "games", fetch = FetchType.LAZY)
    private Set<User> users;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "game_platform",
        joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "platform_id", referencedColumnName = "id")}
    )
    private Set<Platform> gamePlatforms;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "game_genre",
        joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id")}
    )
    private Set<Genre> gameGenres;

    public Game() {}

    @Override
    public String toString() {
        return "Game [description=" + description + ", developer=" + developer + ", gameGenres=" + gameGenres
                + ", gamePlatforms=" + gamePlatforms + ", id=" + id + ", name=" + name + ", price=" + price
                + ", publisher=" + publisher + ", releaseDate=" + releaseDate + ", users=" + users + "]";
    }
}

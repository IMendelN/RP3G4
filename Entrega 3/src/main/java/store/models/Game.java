package store.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private BigDecimal price;

    @ManyToMany(mappedBy = "games", fetch = FetchType.LAZY)
    private Set<User> users;

    public Game() {}
}

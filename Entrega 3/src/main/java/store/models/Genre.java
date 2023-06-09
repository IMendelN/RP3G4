package store.models;

import java.util.HashSet;
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
public class Genre {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
  private Set<User> users = new HashSet<>();

  @ManyToMany(mappedBy = "gameGenres", fetch = FetchType.LAZY)
  private Set<Game> games = new HashSet<>();

  public Genre() {}
}

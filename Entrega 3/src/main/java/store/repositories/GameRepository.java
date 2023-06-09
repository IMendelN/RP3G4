package store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import store.models.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}

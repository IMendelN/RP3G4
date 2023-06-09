package store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import store.models.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}

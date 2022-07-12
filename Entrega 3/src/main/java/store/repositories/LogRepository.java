package store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import store.models.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}

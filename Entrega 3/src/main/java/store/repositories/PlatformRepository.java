package store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import store.models.Platform;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
}

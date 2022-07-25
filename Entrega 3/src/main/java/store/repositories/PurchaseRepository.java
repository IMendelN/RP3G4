package store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import store.models.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}

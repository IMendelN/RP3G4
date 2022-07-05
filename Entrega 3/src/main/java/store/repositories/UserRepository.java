package store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import store.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
  @Query(
    value = "SELECT * FROM user WHERE email = :email AND password = :password",
    nativeQuery = true
  )
  public abstract User login(String email, String password);
}

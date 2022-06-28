package store.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import store.models.entities.User;
import store.repositories.UserRepository;

@Service
public class UserService implements UserRepository {
    private List<User> users = List.of(
        new User(1L, "Tester 1", "123", "address 1", "email 1", "birthDate 1", 1),
        new User(2L, "Tester 2", "456", "address 2", "email 2", "birthDate 2", 2),
        new User(3L, "Tester 3", "789", "address 3", "email 3", "birthDate 3", 3)
    );

    public List<User> listAll() {
        return users;
    }

    public User findById(long id) {
        return users.stream()
            .filter(user -> user.getId().equals(id))
            .findFirst()
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado.")
            );
    }
}

package store.services;

import java.util.List;

import org.springframework.stereotype.Service;

import store.models.entities.User;

@Service
public class TestService {
    public List<User> listAll() {
        return List.of(
            new User(1L, "Tester", "password", "address", "email", "birthDate", 3),
            new User(1L, "Tester 2", "password", "address", "email", "birthDate", 2),
            new User(1L, "Tester 3", "password", "address", "email", "birthDate", 1)
        );
    }
}

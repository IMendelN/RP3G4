package store.repository;

import java.util.List;

import store.models.entities.User;

public interface TestRepository {
    List<User> listAll();
}

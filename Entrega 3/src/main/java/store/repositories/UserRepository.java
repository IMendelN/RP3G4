package store.repositories;

import java.util.List;

import store.models.entities.User;

public interface UserRepository {
    List<User> listAll();
    User findById(long id);
}

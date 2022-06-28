package store.models.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import store.models.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
   
    @GetMapping
    public ResponseEntity<User> findAll() {
        User u = new User(1L, "Matheus Boeira", "12345", 
            "Rua tal, 55", "email@gmail.com", "01/01/0001", 3);
        return ResponseEntity.ok().body(u);
    }
}

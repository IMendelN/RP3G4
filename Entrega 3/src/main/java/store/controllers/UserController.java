package store.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import store.models.entities.User;
import store.services.UserService;
import store.utils.DateUtil;

@RestController
@RequestMapping("/users")
@Log4j2 // Apenas para fins de teste!
@RequiredArgsConstructor
public class UserController {
    private final DateUtil dateUtil;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> list() {
        log.info(dateUtil.formatDateToDatabase(LocalDate.now())); // Apenas para fins de teste!
        return ResponseEntity.ok(userService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable long id) {
        return ResponseEntity.ok(userService.findById(id));
    }
}

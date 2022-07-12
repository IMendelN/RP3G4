package store.controllers.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/users")
public class UserController {   
    @GetMapping
    public String list() {
        return "admin/users";
    }

    @GetMapping("/users/add")
    public String add() {
        return "admin/users/create";
    }

    @GetMapping("/users/{id}/edit")
    public String edit() {
        return "admin/users/?/edit";
    }

    @GetMapping("/users/{id}/delete")
    public String delete() {
        return "admin/users/?/delete";
    }
}

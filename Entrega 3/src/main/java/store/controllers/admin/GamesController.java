package store.controllers.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/games")
public class GamesController {
    @GetMapping
    public String list() {
        return "admin/games";
    }

    @GetMapping("/add")
    public String add() {
        return "admin/games/add";
    }

    @GetMapping("/{id}/edit")
    public String edit() {
        return "admin/games/?/edit";
    }

    @GetMapping("/{id}/delete")
    public String delete() {
        return "admin/users/?/delete";
    }
}

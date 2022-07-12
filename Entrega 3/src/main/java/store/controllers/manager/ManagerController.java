package store.controllers.manager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/manageger/games")

public class ManagerController {
    @GetMapping
    public String list() {
        return "manager/games";
    }

    @GetMapping("/add")
    public String add() {
        return "manager/games/add";
    }

    @GetMapping("/{id}/edit")
    public String edit() {
        return "manager/games/?/edit";
    }

    @GetMapping("/{id}/delete")
    public String delete() {
        return "manager/users/?/delete";
    }

}
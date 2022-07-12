package store.controllers.seller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    @RequestMapping("/seller/games")

    public class SellerController {
        @GetMapping
        public String list() {
            return "seller/games";
        }

        @GetMapping("/add")
        public String add() {
            return "seller/games/add";
        }

        @GetMapping("/{id}/edit")
        public String edit() {
            return "seller/games/?/edit";
        }

        @GetMapping("/{id}/delete")
        public String delete() {
            return "seller/users/?/delete";
        }

    }

package store.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import store.services.GameService;

@Controller
public class AdminController {
    @Autowired
    private GameService gameService;
    
    @GetMapping("/admin")
    public ModelAndView index() {
        return new ModelAndView("admin/index")
            .addObject("games", gameService.findAll());
    }
}

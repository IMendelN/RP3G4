package store.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import store.models.User;
import store.services.GameService;

@Controller
@RequestMapping("/")
public class StoreController { 
    @Autowired
    private GameService gameService;
    
    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index")
            .addObject("games", gameService.findAll());
    }

    @GetMapping("/logout")
    public ModelAndView logout(User user) {
        return new ModelAndView("redirect:/signin");
    }

    @GetMapping("/store/{id}/buy")
    public ModelAndView buy(@PathVariable Long id, User user) {
        return new ModelAndView("store/buy")
            .addObject("game", gameService.findById(id));
    }

    @PostMapping("/store/{id}/buy")
    public ModelAndView buy(@PathVariable Long id, User user, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success", "Compra encerrada com sucesso! Aguardando confirmação do pagamento.");
        return new ModelAndView("redirect:/store/" + id + "/buy");
    }

    @GetMapping("store/buys")
    public ModelAndView buys(HttpSession session) {
        var user = (User) session.getAttribute("user");
        
        return new ModelAndView("store/buys")
            .addObject("games", gameService.findAll())
            .addObject("purchase", user.getPurchases());
    }
}

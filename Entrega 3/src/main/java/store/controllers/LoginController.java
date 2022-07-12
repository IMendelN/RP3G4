package store.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import store.models.User;
import store.repositories.UserRepository;
import store.utils.Attribute;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ModelAndView index(User user, HttpSession session) {
        return new ModelAndView("login");
    }

    @PostMapping
    public ModelAndView login(Model model, User user, HttpSession session) {
        var userFound = userRepository.login(user.getEmail(), user.getPassword());

        if (userFound != null) {
            Attribute.setUserAttributes(session, userFound);
            return new ModelAndView("redirect:/");
        }
        model.addAttribute("error", "E-mail ou senha inválidos.");
        return new ModelAndView("login");
    }
}

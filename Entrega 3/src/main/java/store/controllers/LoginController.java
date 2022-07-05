package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import store.models.User;
import store.repositories.UserRepository;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String index(User user) {
        return "login";
    }

    @PostMapping
    public String login(Model model, User user) {
        var userFound = userRepository.login(user.getEmail(), user.getPassword());

        if (userFound != null) 
            return "redirect:/";

        model.addAttribute("error", "E-mail ou senha inválidos.");
        return "login";
    }
}

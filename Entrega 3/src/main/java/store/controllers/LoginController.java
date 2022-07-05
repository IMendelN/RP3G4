package store.controllers;

import javax.servlet.http.HttpSession;

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
    public String index(User user, HttpSession session) {
        session.invalidate();
        return "login";
    }

    @PostMapping
    public String login(Model model, User user, HttpSession session) {
        var userFound = userRepository.login(user.getEmail(), user.getPassword());

        if (userFound != null) {
            session.setAttribute("logged", true);
            session.setAttribute("role", userFound.getRole().VALUE);
            return "redirect:/";
        }
        model.addAttribute("error", "E-mail ou senha inválidos.");
        return "login";
    }
}

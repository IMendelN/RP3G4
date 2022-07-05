package store.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import store.DTO.UserDTO;
import store.models.enums.UserRole;
import store.repositories.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ModelAndView list() {
        return new ModelAndView("users/list")
            .addObject("users", userRepository.findAll());
    }

    @GetMapping("/new")
    public ModelAndView newUser(UserDTO userDTO) {
        return new ModelAndView("users/new")
            .addObject("errorMessage", "")
            .addObject("roles", UserRole.values());
    }

    @PostMapping
    public ModelAndView create(@Valid UserDTO userDTO, BindingResult result) {
        if (result.hasErrors())
            return new ModelAndView("users/new")
                .addObject("roles", UserRole.values());
        
        userRepository.save(userDTO.toUser());
        return new ModelAndView("redirect:/users")
            .addObject("roles", UserRole.values());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable long id) {
        return "teste";
    }
}

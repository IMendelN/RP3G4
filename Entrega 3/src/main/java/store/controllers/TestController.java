package store.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import store.DTO.UserDTO;
import store.models.enums.UserRole;
import store.services.UserService;

@Controller
@RequestMapping("/users")
public class TestController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView list() {
        return new ModelAndView("users/list")
            .addObject("users", userService.findAll());
    }

    @GetMapping("/new")
    public ModelAndView create(UserDTO userDTO) {
        return new ModelAndView("users/new")
            .addObject("errorMessage", "")
            .addObject("roles", UserRole.values());
    }

    @PostMapping
    public ModelAndView create(@Valid UserDTO userDTO, BindingResult result) {
        if (result.hasErrors())
            return new ModelAndView("users/new")
                .addObject("roles", UserRole.values());
        
        userService.save(userDTO.toUser());
        return new ModelAndView("redirect:/users")
            .addObject("roles", UserRole.values());
    }
}

package store.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import store.models.User;

@Controller
@RequestMapping("/")
public class StoreController {   
    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/logout")
    public ModelAndView logout(User user, HttpSession session) {
        return new ModelAndView("redirect:/signin");
    }
}

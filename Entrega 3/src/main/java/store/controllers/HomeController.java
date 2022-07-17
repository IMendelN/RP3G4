package store.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import store.DTO.RegisterUserDTO;
import store.models.User;
import store.services.GenreService;
import store.services.PlatformService;
import store.services.UserService;
import store.utils.DateUtil;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private PlatformService platformService;

    @GetMapping("/signin")
    public ModelAndView index(User user, HttpSession session) {
        return new ModelAndView("signin");
    }

    @PostMapping("/signin")
    public ModelAndView login(Model model, User user, HttpSession session) {
        var userFound = userService.login(user.getEmail(), user.getPassword());
        
        if (userFound != null) {
            session.setAttribute("user", userFound);
            return new ModelAndView("redirect:/");
        }
        model.addAttribute("error", "E-mail ou senha inválidos.");
        return new ModelAndView("signin");
    }

    @RequestMapping("/signup")
    public ModelAndView signup(RegisterUserDTO registerUserDTO) {       
        return new ModelAndView("signup")
            .addObject("genres", genreService.findAll())
            .addObject("platforms", platformService.findAll());
    }

    @PostMapping("/create")
    public ModelAndView signup( 
        @Valid RegisterUserDTO registerUserDTO, 
        BindingResult result,
        HttpServletRequest request
    ) {
        String date             = request.getParameter("date");
        String[] genres         = request.getParameterValues("genres");
        String[] platforms      = request.getParameterValues("platforms");
        
        if (result.hasErrors())
            return new ModelAndView("signup")
                .addObject("genres", genreService.findAll())
                .addObject("platforms", platformService.findAll());
        
        if (userService.findByEmail(registerUserDTO.getEmail()) != null)
            return new ModelAndView("signup")
                .addObject("error", "E-mail já cadastrado no sistema.")
                .addObject("genres", genreService.findAll())
                .addObject("platforms", platformService.findAll());;

        if (date != null)
            registerUserDTO.setBirthDate(DateUtil.formatStringToDate(date));

        if (genres != null)
            for (String genre : genres) 
                registerUserDTO.getGenres().add(genreService.findById(Long.parseLong(genre)));

        if (platforms != null)
            for (String platform : platforms)
                registerUserDTO.getPlatforms().add(platformService.findById(Long.parseLong(platform)));
        
        userService.save(registerUserDTO.toUser());
        return new ModelAndView("redirect:/signin");
    }
}

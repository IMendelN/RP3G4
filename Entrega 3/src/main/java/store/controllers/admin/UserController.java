package store.controllers.admin;

import javax.servlet.http.HttpServletRequest;
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
import store.services.GenreService;
import store.services.PlatformService;
import store.services.UserService;
import store.utils.DateUtil;

@Controller
@RequestMapping("/admin/users")
public class UserController {  
    @Autowired
    private UserService userService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private PlatformService platformService;

    @GetMapping
    public ModelAndView list() {
        return new ModelAndView("admin/user/list")
            .addObject("users", userService.findAll());
    }

    @GetMapping("/add")
    public ModelAndView add(UserDTO userDTO) {
        return new ModelAndView("admin/user/add")
            .addObject("genres", genreService.findAll())
            .addObject("platforms", platformService.findAll())
            .addObject("roles", UserRole.values());
    }
    
    @PostMapping("/create")
    public ModelAndView create( 
        @Valid UserDTO userDTO, 
        BindingResult result,
        HttpServletRequest request
    ) {
        String date             = request.getParameter("date");
        String[] genres         = request.getParameterValues("genres");
        String[] platforms      = request.getParameterValues("platforms");
        
        if (result.hasErrors())
            return new ModelAndView("admin/user/add")
                .addObject("genres", genreService.findAll())
                .addObject("platforms", platformService.findAll())
                .addObject("roles", UserRole.values());
        
        if (userService.findByEmail(userDTO.getEmail()) != null)
            return new ModelAndView("admin/user/add")
                .addObject("error", "E-mail já cadastrado no sistema.")
                .addObject("genres", genreService.findAll())
                .addObject("platforms", platformService.findAll())
                .addObject("roles", UserRole.values());

        if (date != null)
            userDTO.setBirthDate(DateUtil.formatStringToDate(date));

        if (genres != null)
            for (String genre : genres) 
                userDTO.getGenres().add(genreService.findById(Long.parseLong(genre)));

        if (platforms != null)
            for (String platform : platforms)
                userDTO.getPlatforms().add(platformService.findById(Long.parseLong(platform)));

        userService.save(userDTO.toUser());
        return new ModelAndView("admin/user/add")
            .addObject("success", "Usuário cadastrado com sucesso!")
            .addObject("genres", genreService.findAll())
            .addObject("platforms", platformService.findAll())
            .addObject("roles", UserRole.values());
    }

    @GetMapping("/{id}/edit")
    public String edit() {
        return "admin/users/?/edit";
    }

    @GetMapping("/{id}/delete")
    public String delete() {
        return "admin/users/?/delete";
    }
}

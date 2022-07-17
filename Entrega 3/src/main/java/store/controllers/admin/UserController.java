package store.controllers.admin;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import store.DTO.UserDTO;
import store.models.User;
import store.models.enums.UserRole;
import store.services.GenreService;
import store.services.PlatformService;
import store.services.UserService;
import store.utils.App;
import store.utils.DateUtil;
import store.utils.enums.Color;

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

        userService.save(userDTO.toUser());
        return new ModelAndView("admin/user/add")
            .addObject("success", "Usuário cadastrado com sucesso!")
            .addObject("genres", genreService.findAll())
            .addObject("platforms", platformService.findAll())
            .addObject("roles", UserRole.values());
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, User user) {
        return new ModelAndView("admin/user/edit")
            .addObject("user", userService.findById(id))
            .addObject("genres", genreService.findAll())
            .addObject("platforms", platformService.findAll())
            .addObject("roles", UserRole.values())
            .addObject("date", DateUtil.formatDate(userService.findById(id).getBirthDate()));
    }

    @PostMapping("/{id}/edit")
    public ModelAndView update(
        @PathVariable Long id, 
        @Valid User user, 
        BindingResult result,
        HttpServletRequest request,
        Model model
    ) {
        String birthDate             = request.getParameter("date");
        user.setBirthDate(DateUtil.formatStringToDate(birthDate));

        if (result.hasErrors()) {
            model.addAttribute("error", "Um erro ocorreu enquanto você editava o usuário.");
            return new ModelAndView("redirect:/admin/users/" + id + "/edit");
        }
        
        if (userService.findById(id).getEmail() != user.getEmail()) {
            if (userService.findByEmail(user.getEmail()).getEmail() != null) {
                return new ModelAndView("/admin/users/" + id + "/edit")
                    .addObject("error", "E-mail já cadastrado no sistema.")
                    .addObject("genres", genreService.findAll())
                    .addObject("platforms", platformService.findAll())
                    .addObject("roles", UserRole.values());
            }
        }
        user.setBirthDate(DateUtil.formatStringToDate(birthDate));
        userService.save(user);
        return new ModelAndView("redirect:/admin/users/" + id + "/edit");
    }

    @GetMapping("/{id}/delete")
    public String delete() {
        return "admin/users/?/delete";
    }
}

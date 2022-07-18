package store.controllers.admin;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import store.models.Game;
import store.services.GameService;
import store.services.GenreService;
import store.services.PlatformService;
import store.utils.DateUtil;

@RestController
@RequestMapping("/admin/games")
public class GamesController {
    @Autowired
    private GameService gameService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private PlatformService platformService;

    @GetMapping
    public ModelAndView list() {
        return new ModelAndView("admin/game/list")
            .addObject("games", gameService.findAll());
    }

    @GetMapping("/add")
    public ModelAndView add(Game game) {
        return new ModelAndView("admin/game/add")
            .addObject("genres", genreService.findAll())
            .addObject("platforms", platformService.findAll());
    }

    @PostMapping("/create")
    public ModelAndView create(Game game, HttpServletRequest request, RedirectAttributes redirect) {
        System.out.println(game);
        String dateRelease = request.getParameter("date");

        game.setReleaseDate(DateUtil.formatStringToDate(dateRelease));
        gameService.save(game);

        redirect.addFlashAttribute("success", "Jogo cadastrado com sucesso.");
        return new ModelAndView("redirect:/admin/games");
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, Game game) {
        return new ModelAndView("admin/game/edit")
            .addObject("game", gameService.findById(id))
            .addObject("genres", genreService.findAll())
            .addObject("platforms", platformService.findAll())
            .addObject("date", DateUtil.formatDate(gameService.findById(id).getReleaseDate()));
    }

    @PostMapping("/{id}/edit")
    public ModelAndView update(
        @PathVariable Long id, 
        @Valid Game game,
        HttpServletRequest request,
        RedirectAttributes redirect
    ) {
        String dateRelease = request.getParameter("date");
        game.setReleaseDate(DateUtil.formatStringToDate(dateRelease));
        gameService.save(game);
        redirect.addFlashAttribute("success", "Jogo atualizado com sucesso!");
        return new ModelAndView("redirect:/admin/games");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(
        @PathVariable Long id, 
        RedirectAttributes redirect
    ) {
        Game game = gameService.findById(id);

        if (game == null) {
            redirect.addFlashAttribute("error", "Jogo não encontrado.");
            return new ModelAndView("redirect:/admin/games");
        }
        gameService.delete(id);
        redirect.addFlashAttribute("success", "Jogo excluído com sucesso!");
        return new ModelAndView("redirect:/admin/games");
    }
}

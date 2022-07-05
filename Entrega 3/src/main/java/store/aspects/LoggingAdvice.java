package store.aspects;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import store.utils.App;
import store.utils.DateUtil;
import store.utils.enums.Color;

@Aspect
@Component
public class LoggingAdvice {
    @Autowired
    private HttpSession session;

    public static void logger(String message) {
        App.printf(Color.YELLOW_BRIGHT, DateUtil.formatDateTime(LocalDateTime.now()) + "\t");
        App.printf(Color.GREEN, " INFO");
        App.printf(Color.PURPLE, " 8080\t");
        App.printf(Color.RED, message);
    }
    
    @Around("execution(* store.controllers.HomeController.*(..)) && !execution(* store.controllers.LoginController.*(..)))")
    public String isLogged(JoinPoint joinPoint) {
        if (session.getAttribute("logged") == null) {
            logger("Permissão negada. Redirecionando para a tela inicial.\n");
            return "redirect:/login";
        }
        return "/index";
    }

    @Around("execution(* store.controllers.UserController.*(..))")
    public ModelAndView userAccess(JoinPoint joinPoint) {
        if (session.getAttribute("logged") == null) {
            logger("Permissão negada. Redirecionando para a tela inicial.\n");
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("/index");
    }
}

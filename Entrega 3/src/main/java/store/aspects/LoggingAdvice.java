package store.aspects;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
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

    private static final String MESSAGE_ERROR = "Permissão negada. Redirecionando para a tela inicial.";

    public static void logger(String message) {
        App.printf(Color.YELLOW_BRIGHT, DateUtil.formatDateTime(LocalDateTime.now()) + "\t");
        App.printf(Color.GREEN, " INFO");
        App.printf(Color.PURPLE, " 8080\t");
        App.printf(Color.RED, message);
    }
    
    @Around("execution(* store.controllers.HomeController.*(..))")
    public String isLogged() {
        if (session.getAttribute("logged") == null) {
            logger(MESSAGE_ERROR);
            return "redirect:/login";
        }
        return "/index";
    }

    @Around("execution(* store.controllers.UserController.*(..))")
    public ModelAndView userAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        int role = (Integer) session.getAttribute("role");
        Object isLogged = session.getAttribute("logged");

        if (isLogged == null) {
            logger(MESSAGE_ERROR);
            return new ModelAndView("redirect:/login");
        }

        if (role != 3) {
            logger(MESSAGE_ERROR);
            return new ModelAndView("redirect:/");
        }
        return (ModelAndView) joinPoint.proceed();
    }
}

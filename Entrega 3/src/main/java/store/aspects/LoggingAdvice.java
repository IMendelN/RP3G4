package store.aspects;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import store.models.Log;
import store.models.User;
import store.models.enums.TypeLog;
import store.services.LogService;
import store.services.UserService;
import store.utils.App;
import store.utils.DateUtil;
import store.utils.enums.Color;

@Aspect
@Component
public class LoggingAdvice {
    @Autowired private HttpSession session;
    @Autowired private LogService logService;
    @Autowired private UserService userService;

    private static final String MESSAGE_ERROR = "Login não detectado. Redirecionando para a tela de login.";
    private static final String MESSAGE_NOT_ADMIN = "Permissão negada. Redirecionando para a tela inicial.";

    /**
     * Método responsável em deixar um log no terminal caso um erro
     * ocorra.
     * 
     * @param message a mensagem de erro
     */
    public void log(String message) {
        App.printf(Color.YELLOW_BRIGHT, DateUtil.formatDateTime(LocalDateTime.now()) + "\t");
        App.printf(Color.GREEN, " INFO");
        App.printf(Color.PURPLE, " 8080\t");
        App.printf(Color.RED, message + "\n");
    }

    /**
     * Método responsável em deixar um log no terminal caso um erro
     * ocorra e salvar no banco de dados.
     * 
     * @param message a mensagem de erro
     */
    public void logAndSave(Long userId, TypeLog type, String message, ProceedingJoinPoint joinPoint) {
        log(message);
        logService.save(
            new Log(
                userId, 
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), 
                type,
                message, 
                LocalDateTime.now())
        );
    }

    @Pointcut("execution(* store.controllers.UserController.*(..))")        void userController() {}
    @Pointcut("execution(* store.controllers.UserController.*(..))")        void homeController() {}
    @Pointcut("execution(* store.controllers.HomeController.logout(..))")   void homeControllerLogout() {}

    @Around("homeController()")
    public ModelAndView isLogged(ProceedingJoinPoint joinPoint) throws Throwable {
        if (session.getAttribute("logged") == null) {
            log(MESSAGE_ERROR);
            return new ModelAndView("redirect:/login");
        }
        return (ModelAndView) joinPoint.proceed();
    }

    @After("homeControllerLogout()")
    public void logout() {
        User user = userService.findById((Long) session.getAttribute("userId"));
        log("Usuário '" + user.getName() + "'' deslogou do sistema.");
        session.invalidate();
    }

    @Around("userController()")
    public ModelAndView userAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        Object role = session.getAttribute("role");
        Object isLogged = session.getAttribute("logged");
        Object userId = session.getAttribute("userId");

        if (isLogged == null) {
            log(MESSAGE_ERROR);
            return new ModelAndView("redirect:/login");
        }

        if ((Integer) role != 3) {
            logAndSave(
                Long.parseLong(userId.toString()), 
                TypeLog.ERROR,
                MESSAGE_NOT_ADMIN,
                joinPoint
            );
            return new ModelAndView("redirect:/");
        }
        return (ModelAndView) joinPoint.proceed();
    }
}

package store.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import store.models.enums.TypeLog;
import store.services.UserService;



import store.utils.App;
import store.utils.DateUtil;
import store.utils.enums.Color;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAdvice {
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;
    @Autowired
    private Logger logger;


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
    @Pointcut("execution(* store.controllers.manager.*.*(..))")                 void pointcutManager() {}
    @Pointcut("execution(* store.controllers.seller.*.*(..))")                  void pointcutSeller() {}

    @Around("pointcutManager()")
    public String managerArea(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer role = (Integer) session.getAttribute("role");
        Long userId = (Long) session.getAttribute("userId");

        if (role != null && userId != null && role >= 2) {
            return (String) joinPoint.proceed();
        }
        logger.logAndSave(userId, TypeLog.ERROR, logger.MESSAGE_NOT_ADMIN, joinPoint);
        return "Área permitida para gerentes ou administradores.";
    }

    @Around("pointcutSeller()")
    public String sellerArea(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer role = (Integer) session.getAttribute("role");
        Long userId = (Long) session.getAttribute("userId");

        if (role != null && userId != null && role == 1 || role == 3) {
            return (String) joinPoint.proceed();
        }
        logger.logAndSave(userId, TypeLog.ERROR, logger.MESSAGE_NOT_ADMIN, joinPoint);
        return "Área permitida para vendedores ou administradores.";
}

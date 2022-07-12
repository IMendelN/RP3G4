package store.aspects;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import store.models.enums.TypeLog;
import store.services.UserService;

@Aspect
@Component
public class LoggingAdvice {
    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;
    @Autowired
    private Logger logger;
    
    @Pointcut("execution(* store.controllers.LoginController.*(..))")           void pointcutLogin() {}
    @Pointcut("execution(* store.controllers.LoginController.login(..))")       void pointcutAfterLogin() {}
    @Pointcut("execution(* store.controllers.*.*(..))")                         void pointcutAuth() {}
    @Pointcut("execution(* store.controllers.StoreController.logout(..))")      void pointcutLogout() {}
    @Pointcut("execution(* store.controllers.admin.*.*(..))")                   void pointcutAdmin() {}

    @Around("pointcutAuth() && !pointcutLogin()")
    public ModelAndView isAuthenticated(ProceedingJoinPoint joinPoint) throws Throwable {
        Boolean isAuthenticated = (Boolean) session.getAttribute("auth");

        if (isAuthenticated != null && isAuthenticated) {
            return (ModelAndView) joinPoint.proceed();
        }
        return new ModelAndView("redirect:/login");
    }

    @After("pointcutAfterLogin()")
    public void afterLogin() {
        var id = (Long) session.getAttribute("userId");

        if (id != null) {
            logger.log(
                String.format("Usuário '%s' entrou no sistema.", 
                userService.findById(id).getName()), 
                TypeLog.INFO
            );
        }
    }

    @Around("pointcutLogout()")
    public ModelAndView logout() {
        var id = (Long) session.getAttribute("userId");
        logger.log(String.format("Usuário '%s' saiu do sistema.", userService.findById(id).getName()), TypeLog.INFO);
        session.invalidate();
        return new ModelAndView("redirect:/login");
    }

    @Around("pointcutAdmin()")
    public String adminArea(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer role = (Integer) session.getAttribute("role");
        Long userId = (Long) session.getAttribute("userId");

        if (role != null && userId != null && role == 3) {
            return (String) joinPoint.proceed();
        }
        logger.logAndSave(userId, TypeLog.ERROR, logger.MESSAGE_NOT_ADMIN, joinPoint);
        return "Área permitida para somente administradores.";
    }
}

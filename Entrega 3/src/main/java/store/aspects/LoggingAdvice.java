package store.aspects;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import store.models.User;
import store.models.enums.TypeLog;

@Aspect
@Component
public class LoggingAdvice {
    @Autowired
    private HttpSession session;
    @Autowired
    private Logger logger;
    
    @Pointcut("execution(* store.controllers.HomeController.*(..))")            void pointcutLogin() {}
    @Pointcut("execution(* store.controllers.HomeController.signup(..))")       void pointcutRegister() {}
    @Pointcut("execution(* store.controllers.HomeController.login(..))")        void pointcutAfterLogin() {}
    @Pointcut("execution(* store.controllers.*.*(..))")                         void pointcutAuth() {}
    @Pointcut("execution(* store.controllers.StoreController.logout(..))")      void pointcutLogout() {}
    @Pointcut("execution(* store.controllers.admin.*.*(..))")                   void pointcutAdmin() {}
    @Pointcut("execution(* store.controllers.admin.UserController.*(..))")      void pointcutUser() {}

    @Around("pointcutAuth() && !pointcutLogin() && !pointcutRegister()")
    public ModelAndView isAuthenticated(ProceedingJoinPoint joinPoint) throws Throwable {
        var user = (User) session.getAttribute("user");

        if (user != null) {
            return (ModelAndView) joinPoint.proceed();
        }
        logger.logAndSave(
            null, 
            TypeLog.INFO, 
            "Tentativa de acesso sem autenticação.", 
            joinPoint
        );
        return new ModelAndView("redirect:/signin");
    }

    @After("pointcutAfterLogin()")
    public void afterLogin(JoinPoint joinPoint) {
        var user = (User) session.getAttribute("user");
        
        if (user != null) {
            logger.logAndSave(
                user.getId(),
                TypeLog.INFO,
                String.format("Usuário '%s' entrou no sistema.", user.getName()), 
                joinPoint
            );
        }
    }

    @Around("pointcutLogout()")
    public ModelAndView logout(ProceedingJoinPoint joinPoint) throws Throwable {
        var user = (User) session.getAttribute("user");

        if (user != null) {
            logger.logAndSave(
                user.getId(),
                TypeLog.INFO,
                String.format("Usuário '%s' saiu do sistema.", user.getName()),  
                joinPoint
            );
        }
        session.invalidate();
        return new ModelAndView("redirect:/signin");
    }

    @Around("pointcutAdmin()")
    public ModelAndView adminArea(ProceedingJoinPoint joinPoint) throws Throwable {
        var user = (User) session.getAttribute("user");

        if (user != null && user.getRole().VALUE == 3) {
            logger.logAndSave(
                user.getId(), 
                TypeLog.INFO,
                String.format("Usuário '%s' entrou na área administrativa.", user.getName()), 
                joinPoint
            );
            return (ModelAndView) joinPoint.proceed();
        }
        
        if (user == null) 
            logger.logAndSave(null, TypeLog.WARN, "Tentativa de acesso à área administrativa sem autenticação.", joinPoint);
        else {
            logger.logAndSave(user.getId(), TypeLog.WARN, "Tentativa de acesso à área administrativa sem permissão.", joinPoint);
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("redirect:/signin");
    }
}

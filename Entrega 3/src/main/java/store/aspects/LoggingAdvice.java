// package store.aspects;

// import javax.servlet.http.HttpSession;

// import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.After;
// import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Pointcut;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.ModelAndView;

// import store.models.User;
// import store.models.enums.TypeLog;

// @Aspect
// @Component
// public class LoggingAdvice {
//     @Autowired
//     private HttpSession session;
//     @Autowired
//     private Logger logger;
    
//     @Pointcut("execution(* store.controllers.HomeController.*(..))")            void pointcutLogin() {}
//     @Pointcut("execution(* store.controllers.HomeController.signup(..))")       void pointcutRegister() {}
//     @Pointcut("execution(* store.controllers.HomeController.login(..))")        void pointcutAfterLogin() {}
//     @Pointcut("execution(* store.controllers.*.*(..))")                         void pointcutAuth() {}
//     @Pointcut("execution(* store.controllers.StoreController.logout(..))")      void pointcutLogout() {}
//     @Pointcut("execution(* store.controllers.admin.*.*(..))")                   void pointcutAdmin() {}

//     @Around("pointcutAuth() && !pointcutLogin() && !pointcutRegister()")
//     public ModelAndView isAuthenticated(ProceedingJoinPoint joinPoint) throws Throwable {
//         var user = (User) session.getAttribute("user");

//         if (user != null) {
//             return (ModelAndView) joinPoint.proceed();
//         }
//         return new ModelAndView("redirect:/signin");
//     }

//     @After("pointcutAfterLogin()")
//     public void afterLogin() {
//         var user = (User) session.getAttribute("user");

//         if (user != null) {
//             logger.log(
//                 String.format("Usuário '%s' entrou no sistema.", user.getName()), 
//                 TypeLog.INFO
//             );
//         }
//     }

//     @Around("pointcutLogout()")
//     public ModelAndView logout() {
//         var user = (User) session.getAttribute("user");

//         if (user != null) {
//             logger.log(
//                 String.format("Usuário '%s' saiu do sistema.", user.getName()), 
//                 TypeLog.INFO
//             );
//         }
//         session.invalidate();
//         return new ModelAndView("redirect:/signin");
//     }

//     @Around("pointcutAdmin()")
//     public ModelAndView adminArea(ProceedingJoinPoint joinPoint) throws Throwable {
//         var user = (User) session.getAttribute("user");

//         if (user != null && user.getRole().VALUE == 3)
//             return (ModelAndView) joinPoint.proceed();
        
//         logger.logAndSave(user.getId(), TypeLog.ERROR, logger.MESSAGE_NOT_ADMIN, joinPoint);
//         return new ModelAndView("redirect:/signin");
//     }
// }

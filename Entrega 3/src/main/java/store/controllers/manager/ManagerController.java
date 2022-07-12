package store.controllers.manager;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Pointcut("execution(* store.controllers.manager.*.*(..))")
void pointcutManager(){}

@RestController
@RequestMapping("/manageger/games")

public class ManagerController {
    @GetMapping
    public String list() {
        return "manager/games";
    }

    @GetMapping("/add")
    public String add() {
        return "manager/games/add";
    }

    @GetMapping("/{id}/edit")
    public String edit() {
        return "manager/games/?/edit";
    }

    @GetMapping("/{id}/delete")
    public String delete() {
        return "manager/users/?/delete";
    }

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

}
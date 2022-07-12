package store.controllers.seller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Pointcut("execution(* store.controllers.seller.*.*(..))")
void pointcutSeller() {}

public class SellerController {

    @RestController
    @RequestMapping("/seller/games")

    public class ManagerController {
        @GetMapping
        public String list() {
            return "seller/games";
        }

        @GetMapping("/add")
        public String add() {
            return "seller/games/add";
        }

        @GetMapping("/{id}/edit")
        public String edit() {
            return "seller/games/?/edit";
        }

        @GetMapping("/{id}/delete")
        public String delete() {
            return "seller/users/?/delete";
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

}

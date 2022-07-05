package store.aspects;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import store.utils.App;
import store.utils.DateUtil;
import store.utils.enums.Color;

@Aspect
@Component
public class LoggingAdvice {
    @Autowired
    private HttpSession session;

    @Pointcut("execution(store.controllers.*)")
    public void controllers() {
    }

    public static void logger(String message) {
        App.printf(Color.YELLOW_BRIGHT, DateUtil.formatDateTime(LocalDateTime.now()) + "\t");
        App.printf(Color.GREEN, " INFO");
        App.printf(Color.PURPLE, " 8080\t");
        App.printf(Color.RED, message);
    }
    
    @After("execution(* store.controllers.*.*(..)) && !execution(* store.controllers.LoginController.*(..)))")
    public String isLogged() {
        if (session.getAttribute("logged") == null) {
            logger("Permissão negada. Redirecionando para a tela inicial.\n");
            return "redirect:/login";
        }
        return "";
    }
}

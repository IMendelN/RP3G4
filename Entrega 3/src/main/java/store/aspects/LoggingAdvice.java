package store.aspects;

import java.time.LocalDateTime;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import store.utils.App;
import store.utils.DateUtil;
import store.utils.enums.Color;

@Aspect
@Component
public class LoggingAdvice {
    
    @After("execution(* store.controllers.*.*(..))")
    public void logging() {
        App.printf(Color.YELLOW_BRIGHT, DateUtil.formatDateTime(LocalDateTime.now()) + "\t");
        App.printf(Color.GREEN, " INFO");
        App.printf(Color.PURPLE, " 8080\t");
        App.println(Color.CYAN, "Alguma coisa aconteceu!");
    }
}

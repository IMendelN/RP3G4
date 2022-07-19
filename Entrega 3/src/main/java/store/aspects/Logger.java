package store.aspects;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import store.models.Log;
import store.models.enums.TypeLog;
import store.services.LogService;
import store.utils.App;
import store.utils.DateUtil;
import store.utils.enums.Color;

@Component
public class Logger {
    @Autowired 
    private LogService logService;

    public final String MESSAGE_ERROR = "Login não detectado. Redirecionando para a tela de login.";
    public final String MESSAGE_NOT_ADMIN = "Permissão negada. Redirecionando para a tela inicial.";

    public static final Color whichColor(TypeLog type) {
        switch (type) {
            case INFO :{
                return Color.CYAN;
            }
            case WARN : {
               return Color.YELLOW;
            }
            case ERROR : {
                return Color.RED;
            }
            default : {
                return Color.NONE;
            }
        }
    }

    /**
     * Método responsável em deixar um log no terminal caso um erro
     * ocorra.
     * 
     * @param message a mensagem de erro
     */
    public void log(String message, TypeLog type) {
        App.printf(Color.YELLOW_BRIGHT, DateUtil.formatDateTime(LocalDateTime.now()) + "\t");
        App.printf(whichColor(type), " " + type);
        App.printf(Color.PURPLE, " 8080\t");
        App.printf(whichColor(type), message + "\n");
    }

    /**
     * Método responsável em deixar um log no terminal caso um erro
     * ocorra e salvar no banco de dados. Este método é usado para
     * logs com a anotação @Around.
     * 
     * @param message a mensagem de erro
     */
    public void logAndSave(Long userId, TypeLog type, String message, ProceedingJoinPoint joinPoint) {
        log(message, type);
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

    /**
     * Método responsável em deixar um log no terminal caso um erro
     * ocorra e salvar no banco de dados. Este método é usado para
     * logs com a anotação @After ou @Before.
     * 
     * @param message a mensagem de erro
     */
    public void logAndSave(Long userId, TypeLog type, String message, JoinPoint joinPoint) {
        log(message, type);
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
}

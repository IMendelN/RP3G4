package store.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import store.models.enums.TypeLog;

@Entity
@Data
public class Log implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String pathClass;
    private String methodName;
    @Enumerated(EnumType.STRING)
    private TypeLog type;
    private String message;
    private LocalDateTime date;

    Log() {}

    public Log(Long userId, String pathClass, String methodName, TypeLog type, String message, LocalDateTime date) {
        this.userId = userId;
        this.message = message;
        this.date = date;
        this.pathClass = pathClass;
        this.methodName = methodName;
        this.type = type;
    }
}

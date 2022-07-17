package store.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
    public static String formatDateTime(LocalDateTime date) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static LocalDate formatStringToDate(String date) {
        return LocalDate.parse(date);
    }
}

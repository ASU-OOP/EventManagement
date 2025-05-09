package Utils;

import java.time.*;
import java.util.Date;

public class DateUtils {

    public static LocalTime[] getTimeRange() {
        LocalTime[] hourRange = new LocalTime[24];

        for (int i = 0; i < 24; i++) {
            hourRange[i] = LocalTime.of(i, 0);
        }

        return hourRange;
    }
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
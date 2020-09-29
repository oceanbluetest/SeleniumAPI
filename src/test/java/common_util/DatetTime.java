package common_util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatetTime {
    public static final String CURRENT_DATETIME = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss"));
}

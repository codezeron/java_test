package test.api.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final ZoneId BRAZIL_ZONE_ID = ZoneId.of("America/Sao_Paulo");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime convertToBrazilTime(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(BRAZIL_ZONE_ID).toLocalDateTime();
    }

    public static String formatToBrazilTime(LocalDateTime dateTime) {
        return convertToBrazilTime(dateTime).format(FORMATTER);
    }
}
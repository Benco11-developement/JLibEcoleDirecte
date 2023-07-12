package fr.benco11.jlibecoledirecte.lib.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Function;

public class DateUtils {
    private DateUtils() {
    }

    public static Period period(LocalDate a, LocalDate b) {
        return Period.between(a, b);
    }

    private static <T> T parse(String toParse, DateTimeFormatter format, Function<Pair<String, DateTimeFormatter>, T> parseFunction) {
        try {
            return parseFunction.apply(new Pair<>(toParse, format));
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static LocalDate parseLocalDate(String toParse, DateTimeFormatter format) {
        return parse(toParse, format, p -> LocalDate.parse(p.a(), p.b()));
    }

    public static LocalTime parseLocalTime(String toParse, DateTimeFormatter format) {
        return parse(toParse, format, p -> LocalTime.parse(p.a(), p.b()));
    }

    public static LocalDateTime parseLocalDateTime(String toParse, DateTimeFormatter format) {
        return parse(toParse, format, p -> LocalDateTime.parse(p.a(), p.b()));
    }
}

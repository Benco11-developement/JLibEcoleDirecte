package fr.benco11.jlibecoledirecte.lib.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
    private DateUtils() {
    }

    public static Period period(Date a, Date b) {
        return Period.between(localDate(a), localDate(b));
    }

    public static LocalDate localDate(Date a) {
        return LocalDate.ofInstant(a.toInstant(), ZoneId.systemDefault());
    }

    public static DateFormat format(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        return dateFormat;
    }

    public static Date parse(String toParse, DateFormat format) {
        try {
            return format.parse(toParse);
        } catch (ParseException e) {
            return null;
        }
    }
}

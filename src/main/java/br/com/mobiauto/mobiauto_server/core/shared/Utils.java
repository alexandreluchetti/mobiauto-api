package br.com.mobiauto.mobiauto_server.core.shared;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Utils {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parse(String date) {
        if (date == null) return null;

        return LocalDate.parse(date, DATE_TIME_FORMATTER);
    }

    public static String format(LocalDate date) {
        if (date == null) return null;

        return date.format(DATE_TIME_FORMATTER);
    }
}

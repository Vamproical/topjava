package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private DateTimeUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T value, T start, T end) {
        return value.compareTo(start) >= 0 && value.compareTo(end) < 0;
    }

    public static LocalDate parseLocalDate(String date) {
        return date.length() != 0 ? LocalDate.parse(date) : null;
    }

    public static LocalTime parseLocalTime(String time) {
        return time.length() != 0 ? LocalTime.parse(time) : null;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}


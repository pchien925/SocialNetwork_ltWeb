package com.nhom7.socialNetworkApp.util;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class DateTimeFormatter {
    Map<Long, Function<LocalDateTime, String>> strategyMap = new LinkedHashMap<>();

    public DateTimeFormatter() {
        strategyMap.put(60L, this::formatInSeconds);
        strategyMap.put(3600L, this::formatInMinutes);
        strategyMap.put(86400L, this::formatInHours);
        strategyMap.put(Long.MAX_VALUE, this::formatInDate);
    }

    public String format(LocalDateTime dateTime){
        LocalDateTime now = LocalDateTime.now();
        long elapseSeconds = ChronoUnit.SECONDS.between(dateTime, now);

        var strategy = strategyMap.entrySet()
                .stream()
                .filter(entry -> elapseSeconds < entry.getKey())
                .findFirst().get();
        return strategy.getValue().apply(dateTime);
    }

    private String formatInSeconds(LocalDateTime dateTime){
        long elapseSeconds = ChronoUnit.SECONDS.between(dateTime, LocalDateTime.now());
        return String.format("%s second(s) ago", elapseSeconds);
    }

    private String formatInMinutes(LocalDateTime dateTime){
        long elapseMinutes = ChronoUnit.MINUTES.between(dateTime, LocalDateTime.now());
        return String.format("%s minute(s) ago", elapseMinutes);
    }

    private String formatInHours(LocalDateTime dateTime){
        long elapseHours = ChronoUnit.HOURS.between(dateTime, LocalDateTime.now());
        return String.format("%s hour(s) ago", elapseHours);
    }

    private String formatInDate(LocalDateTime dateTime){
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ISO_DATE;
        return dateTime.format(dateTimeFormatter);
    }
}

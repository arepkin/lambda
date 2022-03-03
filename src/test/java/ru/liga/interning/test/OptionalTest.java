package ru.liga.interning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @author Repkin Andrey {@literal <arepkin@at-consulting.ru>}
 */
public class OptionalTest {

    @Test
    public void optionalTest() {
        LocalDate now = LocalDate.now();
        LocalDate lastDate = LocalDate.of(2020, 10, 10);
        String formattedDate = Optional.ofNullable(now)
                .filter(d -> d.isAfter(lastDate))
                .map(DateTimeFormatter.ofPattern("dd.MM.yyy")::format)
                .orElse("NOTHING");
        System.out.println(formattedDate);
        Assertions.assertNotEquals(formattedDate, null);
    }
}

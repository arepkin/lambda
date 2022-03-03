package ru.liga.interning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

/**
 * @author Repkin Andrey {@literal <arepkin@at-consulting.ru>}
 */
public class CurryingTest {

    @Test
    public void curryingTest() {
        final Integer result1 = simpleFunc(1, 2, 3);
        final Integer result2 = curryingFunc(1, 2).apply(3);
        Assertions.assertEquals(result1, result2);
    }

    private Integer simpleFunc(Integer a, Integer b, Integer c) {
        return a * c + b;
    }

    private Function<Integer, Integer> curryingFunc(Integer a, Integer b) {
        return (Integer c) -> a * c + b;
    }
}

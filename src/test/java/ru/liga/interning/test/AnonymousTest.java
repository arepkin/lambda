package ru.liga.interning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.liga.interning.AnonymousInterface;
import ru.liga.interning.EmptyInterface;
import ru.liga.interning.EnumAnonymousFactory;
import ru.liga.interning.SimpleAnonymousFactory;
import ru.liga.interning.SimpleLambdaFactory;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Repkin Andrey {@literal <arepkin@at-consulting.ru>}
 */
public class AnonymousTest {
    private int i = 0;

    @Test
    public void verySimple() {
        final EmptyInterface verySimpleObj = new EmptyInterface() {

        };
        Assertions.assertNotEquals(verySimpleObj, null);
    }

    @Test
    public void typicalAnonymousUsage() {
        final AnonymousInterface obj = new AnonymousInterface() {
            @Override
            public String doSomethingWith(String argument) {
                return MessageFormat.format("{0,number,#}", argument.length());
            }
        };
        Assertions.assertEquals(obj.doSomethingWith("123"), "3");
    }

    @Test
    public void anonymousList() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(5);
        int j = 1;
        final ArrayList<Integer> anonymousList = new ArrayList<>() {{
            add(++AnonymousTest.this.i);
            add(2);
//          j = 2;     ERROR: Variable 'j' is accessed from within inner class, needs to be final or effectively final
            addAll(list);
        }};
        Assertions.assertEquals(this.i, 1);
        Assertions.assertEquals(anonymousList, Arrays.asList(1, 2, 3, 4, 5));
        Assertions.assertNotEquals(anonymousList.getClass(), ArrayList.class);
        Assertions.assertEquals(list.getClass(), ArrayList.class);
    }

    @Test
    public void testAnonymousWithFactory() {
        AnonymousInterface anonymousInstance = new SimpleAnonymousFactory().create();
        Assertions.assertNotEquals(anonymousInstance, null);
    }

    @Test
    public void testAnonymousEnumWithFactory() {
        AnonymousInterface anonymousInstance = EnumAnonymousFactory.INSTANCE;
        Assertions.assertNotEquals(anonymousInstance, null);
    }

    @Test
    public void lambda() {
        Function<Integer, Integer> f = i -> i + 1;
        BiFunction<Integer, Integer, Integer> f2 = (a, b) -> a + b;
        Function<Integer, Function<Integer, Integer>> f12 = a -> b -> a + b;
    }

    @Test
    public void testLambda() {
        AnonymousInterface anonymousInstance = new SimpleLambdaFactory().create();
        Assertions.assertNotEquals(anonymousInstance, null);
    }

    @Test
    public void testCaptureContextAnonymous() {
        final List<String> mutableList = new ArrayList<>(Arrays.asList("Hello "));
        AnonymousInterface anonymous = new AnonymousInterface() {
            @Override
            public String doSomethingWith(String argument) {
                mutableList.add(argument);
                //mutableList = Arrays.asList("Hello ", "world!); //mutableList is Effective final
                return null;
            }
        };
        anonymous.doSomethingWith("world!");
        Assertions.assertEquals(mutableList.stream().collect(Collectors.joining()), "Hello world!");
    }
}

package ru.liga.interning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author Repkin Andrey {@literal <arepkin@at-consulting.ru>}
 */
public class Functional {

    @Test
    public void findMaxImp() {
        List<Integer> list = Arrays.asList(1, 4, 5, 9, -1);
        int max = Integer.MIN_VALUE;
        for (Integer i : list) {
            if (max < i) {
                max = i;
            }
        }
        Assertions.assertEquals(max, 9);
    }

    @Test
    public void findMaxDeclarative() {
        List<Integer> list = Arrays.asList(1, 4, 5, 9, -1);
//        List<Integer> list = IntStream.range(0, 10000).boxed().collect(Collectors.toList()); //раскоментить чтобы словить Stackoverflow в рекурсивном вызове
        final BiFunction<Integer, List<Integer>, Integer> search = new BiFunction<>() {
            @Override
            public Integer apply(Integer i, List<Integer> list) {
                if (list.size() == 0) return i;
                final Integer first = list.get(0);
                return apply(first > i ? first : i, list.subList(1, list.size())); //хвостовая рекурсия в java 17 бесполезна, (не поддерживается), так как не хочет терять инфу о стеке каждого вызова
            }
        };
        Assertions.assertEquals(search.apply(Integer.MIN_VALUE, list), 9);
    }

    @Test
    public void methodLinksTest(){
        BiFunction<Integer, Integer, Integer> sum1 = (Integer a, Integer b) -> a + b;
        BiFunction<Integer, Integer, Integer> sum2 = this::testSum;
        Integer resultSum1 = sum1.apply(1, 2);
        Integer resultSum2 = sum2.apply(1, 2);
        Assertions.assertEquals(resultSum1, resultSum2);
    }

    private Integer testSum(Integer a, Integer b) {
        return a + b;
    }
}

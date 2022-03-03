package ru.liga.interning.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Repkin Andrey {@literal <arepkin@at-consulting.ru>}
 */
public class ComparatorTest {

    @Test
    public void sortArray() {
        final Color grey = new Color(128, 128, 128);
        final Color pink = new Color(188, 143, 143);
        final Color red = new Color(255, 0, 0);
        final Color darkGreen = new Color(47, 79, 47);
        final Color cyan = new Color(0, 255, 255);
        final Color cyan2 = new Color(0, 255, 255, 23);
        final List<Color> colors = List.of(grey, pink, red, darkGreen, cyan, cyan2);
        final Comparator<Color> luminanceComparator = Comparator.comparing(Color::luminance);
        //Сортируем по свечению, сначала самые яркие
        final ArrayList<Color> sortedColors = new ArrayList<>(colors);
        Collections.sort(sortedColors, luminanceComparator);
        Assertions.assertEquals(sortedColors.get(0), red);
        Assertions.assertEquals(sortedColors.get(5), cyan2);

        //Пример странного компаратора, который сначала сортирует по возрастанию свечения, а потом по убыванию прозрачности
        final Comparator<Color> strangeComparator = Comparator
                .comparing(Color::luminance)
                .thenComparing(Color::getX, Comparator.reverseOrder());
        final ArrayList<Color> strangeSortedColors = new ArrayList<>(colors);
        Collections.sort(strangeSortedColors, strangeComparator);
        Assertions.assertEquals(strangeSortedColors.get(0), red);
        Assertions.assertEquals(strangeSortedColors.get(5), cyan);
    }


    class Color {

        int red;
        int green;
        int blue;
        int x;

        public Color(int red, int green, int blue, int x) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.x = x;
        }

        public Color(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.x = 0;
        }

        public int getX() {
            return x;
        }

        float luminance() {
            return 0.2126f * red + 0.7152f * green + 0.0722f * blue;
        }

        @Override
        public String toString() {
            return "Color{" +
                    "red=" + red +
                    ", green=" + green +
                    ", blue=" + blue +
                    ", x=" + x +
                    '}';
        }
    }
}

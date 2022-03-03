package ru.liga.interning;

/**
 * @author Repkin Andrey {@literal <arepkin@at-consulting.ru>}
 */
public class SimpleLambdaFactory {
    public AnonymousInterface create() {
        return argument -> "Lambda: The string has a length of %d symbols".formatted(argument.length());
    }
}

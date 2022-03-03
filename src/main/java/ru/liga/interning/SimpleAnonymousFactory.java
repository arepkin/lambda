package ru.liga.interning;

/**
 * @author Repkin Andrey {@literal <arepkin@at-consulting.ru>}
 */
public class SimpleAnonymousFactory {
    public AnonymousInterface create() {
        return new AnonymousInterface() {
            @Override
            public String doSomethingWith(String argument) {
                return "argument: The string has a length of %d symbols"
                        .formatted(argument.length());
            }
        };
    }
}

package ru.liga.interning;

/**
 * @author Repkin Andrey {@literal <arepkin@at-consulting.ru>}
 */
public enum EnumAnonymousFactory implements AnonymousInterface {

    INSTANCE() {
        @Override
        public String doSomethingWith(String argument) {
            return "Enum: The string has a length of %d symbols".formatted(argument.length());
        }
    }
}

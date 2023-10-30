package ru.markina.hw1.regular;

/**
 * Класс-декоратор с функцией для форматированного вывода числа
 */
public class Decorator {

    /**
     * Метод декорирует число, добавляя к нему строку
     * при помощи функции форматирования строк
     */
    public static String decorate(int a) {
        return String.format("Here is your number: %d.", a);
    }
}

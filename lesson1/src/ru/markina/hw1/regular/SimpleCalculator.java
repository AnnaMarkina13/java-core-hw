package ru.markina.hw1.regular;

/**
 * Класс для проведения четырех базовых математических операций над целыми числами
 */
public class SimpleCalculator {

    /**
     * Метод сложения двух целых чисел
     *
     * @param a первое слагаемое
     * @param b второе слагаемое
     * @return сумма a и b
     */
    public static int sum(final int a, final int b) {
        return a + b;
    }

    /**
     * Метод вычитания одного целого числа из другого
     *
     * @param a уменьшаемое
     * @param b вычитаемое
     * @return разность a и b
     */
    public static int sub(final int a, final int b) {
        return a - b;
    }

    /**
     * Метод перемножения двух целых чисел
     *
     * @param a первый множитель
     * @param b второой множитель
     * @return произведение a и b
     */
    public static int mult(final int a, final int b) {
        return a * b;
    }

    /**
     * Метод деления двух целых чисел
     *
     * @param a делимое
     * @param b делитель
     * @return частное a и b (целое, без остатка)
     */
    public static int div(final int a, final int b) {
        return a / b;
    }
}

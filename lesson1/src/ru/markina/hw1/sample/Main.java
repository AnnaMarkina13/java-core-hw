package ru.markina.hw1.sample;

import ru.markina.hw1.regular.Decorator;
import ru.markina.hw1.regular.SimpleCalculator;

public class Main {

    public static void main(String[] args) {

        int result = SimpleCalculator.sum(4, 5);
        System.out.println(Decorator.decorate(result));
        result = SimpleCalculator.sub(4, 5);
        System.out.println(Decorator.decorate(result));
        result = SimpleCalculator.mult(4, 5);
        System.out.println(Decorator.decorate(result));
        result = SimpleCalculator.div(4, 5);
        System.out.println(Decorator.decorate(result));
    }
}

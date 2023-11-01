package ru.markina.hw3.worker;

import java.util.Objects;

public abstract class Worker implements Comparable<Worker> {

    protected final String name;

    public Worker(final String name) {
        Objects.requireNonNull(name, "Неверно указано имя!");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Имя работника: %s. Категория: %s. Месячная заработная плата: %.2f.",
                name, getClass().getSimpleName(), calculateAverageMonthSalary());
    }

    @Override
    public int compareTo(final Worker o) {
        return name.compareTo(o.name);
    }

    public abstract double calculateAverageMonthSalary();
}

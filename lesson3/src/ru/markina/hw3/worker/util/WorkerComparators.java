package ru.markina.hw3.worker.util;

import ru.markina.hw3.util.Direction;
import ru.markina.hw3.worker.Worker;

import java.util.Comparator;

/**
 * Утилитный класс с реализацией компаратора (сравнивающего по имени и размеру заработной платы)
 * для Worker и его наследников
 */
public class WorkerComparators {

    private WorkerComparators() {
    }

    public static Comparator<Worker> workerByNameComparator() {
        return workerByNameComparator(Direction.ASC);
    }

    public static Comparator<Worker> workerByNameComparator(Direction direction) {
        return direction.applyDirection(Comparator.comparing(Worker::getName));
    }

    public static Comparator<Worker> workerBySalaryComparator() {
        return workerBySalaryComparator(Direction.ASC);
    }

    public static Comparator<Worker> workerBySalaryComparator(Direction direction) {
        return direction.applyDirection(Comparator.comparing(Worker::calculateAverageMonthSalary));
    }

}

package ru.markina.hw3;

import ru.markina.hw3.util.Direction;
import ru.markina.hw3.worker.Employee;
import ru.markina.hw3.worker.Freelancer;
import ru.markina.hw3.worker.util.WorkTeam;
import ru.markina.hw3.worker.util.WorkerComparators;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        WorkTeam workers = new WorkTeam(Arrays.asList(new Employee("Александра", 78920.34),
                new Freelancer("Олег", 120),
                new Freelancer("Дмитрий", 517.5),
                new Employee("Илья", 234890),
                new Employee("Светлана", 32700.54),
                new Freelancer("Валентина", 1000)));

        workers.workers().forEach(System.out::println);

        System.out.println("---------------------------------------------------");

        workers.workers().stream()
                .sorted(WorkerComparators.workerByNameComparator())
                .forEach(System.out::println);

        System.out.println("---------------------------------------------------");

        workers.workers().stream()
                .sorted(WorkerComparators.workerBySalaryComparator())
                .forEach(System.out::println);

        System.out.println("---------------------------------------------------");

        workers.workers().stream()
                .sorted(WorkerComparators.workerBySalaryComparator(Direction.DESC))
                .forEach(System.out::println);
     }
}
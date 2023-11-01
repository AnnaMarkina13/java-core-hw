package ru.markina.hw3.worker;

public class Employee extends Worker {

    private final double fixedMonthPayment;

    public Employee(String name, final double fixedMonthPayment) {
        super(name);
        validateMinSalary(fixedMonthPayment);
        this.fixedMonthPayment = fixedMonthPayment;
    }

    @Override
    public double calculateAverageMonthSalary() {
        return fixedMonthPayment;
    }

    private void validateMinSalary(double fixedMonthPayment) {
        if (fixedMonthPayment <= 16242) {
            throw new RuntimeException("Месячная оплата не может быть меньше МРОТ.");
        }
    }
}

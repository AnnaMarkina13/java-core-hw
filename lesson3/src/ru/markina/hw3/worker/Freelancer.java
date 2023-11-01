package ru.markina.hw3.worker;

public class Freelancer extends Worker {

    private final double hourlyRate;

    public Freelancer(String name, final double hourlyRate) {
        super(name);
        validateMinSalary(hourlyRate);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateAverageMonthSalary() {
        return 20.8 * 8 * hourlyRate;
    }

    private void validateMinSalary(double hourlyRate) {
        if (hourlyRate <= 0) {
            throw new RuntimeException("Почасовая ставка не может быть меньше либо равна 0.");
        }
    }
}

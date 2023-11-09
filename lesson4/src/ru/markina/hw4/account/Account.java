package ru.markina.hw4.account;

import ru.markina.hw4.exception.InsufficientFundsException;

import java.math.BigDecimal;

public abstract class Account {

    private BigDecimal accountBalance;

    public Account(final BigDecimal initBalance) {
        checkInitBalance(initBalance);
        this.accountBalance = initBalance;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void increaseBalance(BigDecimal deposit) {
        checkDeposit(deposit);
        accountBalance = accountBalance.add(deposit);
        System.out.printf("Баланс успешно пополнен! Текущий баланс счета: %s\n",
                accountBalance);
    }

    public void withdrawFunds(BigDecimal withdrawalSum) throws InsufficientFundsException {
        checkWithdrawalSum(withdrawalSum);
        accountBalance = accountBalance.subtract(withdrawalSum);
        System.out.printf("Успешное снятие средств! Текущий баланс счета: %s\n",
                accountBalance);
    }

    private void checkInitBalance(BigDecimal initBalance) {
        if (initBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Начальный баланс счета не может быть отрицательным!");
        }
    }

    private void checkDeposit(BigDecimal deposit) {
        if (deposit.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Невозможно внести отрицательный депозит!");
        }
    }

    private void checkWithdrawalSum(BigDecimal withdrawalSum) throws InsufficientFundsException {
        if (withdrawalSum.compareTo(accountBalance) > 0) {
            throw new InsufficientFundsException("Недостаточно средств на счете для снятия данной суммы! Текущий баланс: "
                    + accountBalance.toString());
        }
    }
}

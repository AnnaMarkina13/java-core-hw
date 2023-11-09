package ru.markina.hw4;

import ru.markina.hw4.account.Account;
import ru.markina.hw4.exception.InsufficientFundsException;

import java.math.BigDecimal;

public class Transaction {

    Account fromAccount;
    Account toAccount;

    public Transaction(final Account fromAccount, final Account toAccount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public void execute(BigDecimal sum) throws InsufficientFundsException {
        fromAccount.withdrawFunds(sum);
        toAccount.increaseBalance(sum);
        System.out.println("Успешный перевод средств!");
    }
}


package ru.markina.hw4;

import ru.markina.hw4.account.Account;
import ru.markina.hw4.account.CreditAccount;
import ru.markina.hw4.account.DebitAccount;
import ru.markina.hw4.exception.InsufficientFundsException;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        try {
            Account debitAccount = new DebitAccount(BigDecimal.valueOf(0));
            Account creditAccount = new CreditAccount(BigDecimal.valueOf(130000));
            Transaction transaction = new Transaction(debitAccount, creditAccount);
            debitAccount.increaseBalance(BigDecimal.valueOf(234554.676));
            debitAccount.withdrawFunds(BigDecimal.valueOf(2234.123));
            debitAccount.increaseBalance(BigDecimal.valueOf(-245));
            debitAccount.withdrawFunds(BigDecimal.valueOf(343333));
            transaction.execute(BigDecimal.valueOf(1204440));

        } catch(IllegalArgumentException | InsufficientFundsException e) {
            System.err.println(e.getMessage());
        }
    }
}

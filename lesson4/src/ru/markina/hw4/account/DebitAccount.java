package ru.markina.hw4.account;

import ru.markina.hw4.account.Account;

import java.math.BigDecimal;

public class DebitAccount extends Account {

    public DebitAccount(final BigDecimal initBalance) {
        super(initBalance);
    }
}

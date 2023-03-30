package com.telran.bank.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

public enum TransactionType {
    /*Bank account transaction types:
     * ATM: Deposit or withdraw funds using an ATM.
     * Deposit: Add funds to an account by any method.
     * Online: Withdraw funds through a web-based store or online banking service.
     * POS: Withdraw funds through a point-of-sale transaction (typically a cash or debit card purchase).
     * Withdrawal: Deduct funds from an account by any method*/
    ATM,
    POS,
    ONLINE,
    DEPOSIT,
    WITHDRAW;
}

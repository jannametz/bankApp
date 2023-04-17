package com.telran.bank.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import static com.telran.bank.entity.enums.TransactionType.*;

/**
 * @author Jana Metz on 17.04.23
 */

    @Getter
    @AllArgsConstructor
    public enum ErrorMessage {
        NOT_ENOUGH_MONEY("Not enough money on the account. id = "),
        TRANSACTION_TYPE(" this transaction type doesn't exist. Available types: " + ATM + ", " + WITHDRAW + ", " + DEPOSIT +", " + ONLINE + ", " + POS),
        DATE_EXCEPTION(" bad date format. Please enter the date in following format yyyy-MM-dd");
        private final String message;
    }


package com.telran.bank.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Jana Metz on 31.03.23
 */
@Getter
@AllArgsConstructor
public enum  ErrorMessage {
    NOT_ENOUGH_MONEY("Not enough money on the account.id = "),
    ACCOUNT_NOT_FOUND("Not found account.cityIn = ");
    private final String message;

    }



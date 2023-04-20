package com.telran.bank.service.util;

import com.telran.bank.entity.enums.TransactionType;
import com.telran.bank.exception.DateException;

import java.time.format.DateTimeParseException;

import static java.util.logging.Level.parse;

/**
 * @author Jana metz on 17.04.23
 */
public class RequestCheck {

    public static void checkDate(String date) {
        if (date == null) return;

        try {
            parse(date);
        } catch (DateTimeParseException e) {
            throw new DateException(date);
        }
    }

}

package com.telran.bank.exception;
import static com.telran.bank.exception.message.ErrorMessage.DATE_EXCEPTION;
/**
 * @author Jana Metz on 17.04.23
 */
public class DateException extends RuntimeException {
    public DateException(String date) {
        super(date + DATE_EXCEPTION.getMessage());
    }
}

package com.telran.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = " Entity that doesn't exist. ")

public class EntityNotFoundException extends RuntimeException {

}


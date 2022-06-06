package com.example.individualproject.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PhoneNumberAlreadyExistsException extends ResponseStatusException {
    public PhoneNumberAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "PHONE-NUMBER_ALREADY_EXISTS");
    }
}

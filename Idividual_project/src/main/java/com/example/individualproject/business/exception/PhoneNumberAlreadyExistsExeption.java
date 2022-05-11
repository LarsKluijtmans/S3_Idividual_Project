package com.example.individualproject.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PhoneNumberAlreadyExistsExeption extends ResponseStatusException {
    public PhoneNumberAlreadyExistsExeption() {super(HttpStatus.BAD_REQUEST, "PHONENUMBER_ALREADY_EXISTS");}
}

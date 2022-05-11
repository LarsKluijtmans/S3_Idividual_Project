package com.example.individualproject.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailAlreadyExistsExeption extends ResponseStatusException {
    public EmailAlreadyExistsExeption() {super(HttpStatus.BAD_REQUEST, "EMAIL_ALREADY_EXISTS");
}
}

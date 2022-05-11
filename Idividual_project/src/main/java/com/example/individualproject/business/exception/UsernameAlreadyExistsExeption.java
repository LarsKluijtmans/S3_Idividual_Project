package com.example.individualproject.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsernameAlreadyExistsExeption extends ResponseStatusException {
    public UsernameAlreadyExistsExeption() {
        super(HttpStatus.BAD_REQUEST, "USERNAME_ALREADY_EXISTS");
    }
}

package com.example.individualproject.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GenreNotFoundException extends ResponseStatusException {
    public GenreNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "GENRE_NOT_FOUND");
    }
}
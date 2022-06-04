package com.example.individualproject.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BuyingYourOwnProductException extends ResponseStatusException {
    public BuyingYourOwnProductException() {
        super(HttpStatus.BAD_REQUEST, "CANT_BUY_YOUR_OWN_PRODUCT");
    }
}


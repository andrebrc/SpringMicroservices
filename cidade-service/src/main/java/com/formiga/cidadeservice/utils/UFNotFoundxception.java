package com.formiga.cidadeservice.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UFNotFoundxception extends RuntimeException {
    public UFNotFoundxception(String message) {
        super(message);
    }
}

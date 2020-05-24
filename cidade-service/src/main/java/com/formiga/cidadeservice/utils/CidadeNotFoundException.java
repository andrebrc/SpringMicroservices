package com.formiga.cidadeservice.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CidadeNotFoundException extends RuntimeException {
    public CidadeNotFoundException(String message) {
        super(message);
    }
}

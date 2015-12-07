package com.example.exceptions;

/**
 * Created by zealot on 05.12.2015.
 */
public class NoSuchAbonentException extends RuntimeException {
    private String message;

    public NoSuchAbonentException(String s) {
    message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

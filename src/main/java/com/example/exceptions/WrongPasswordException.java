package com.example.exceptions;

/**
 * Created by zealot on 05.12.2015.
 */
public class WrongPasswordException extends RuntimeException {
    private String message;
    public WrongPasswordException(String s) {
        message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

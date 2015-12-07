package com.example.validation;

import com.example.business.Response;
import com.example.exceptions.NoSuchAbonentException;
import com.example.exceptions.WrongPasswordException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zealot on 05.12.2015.
 */
@ControllerAdvice
public class AbonentHandler {
    @ExceptionHandler(NoSuchAbonentException.class)
    @ResponseBody
    public Response processValidationAbonentException(NoSuchAbonentException ex) {
     Response response = new Response();
        response.setResultCode(11);
        response.setMessage(ex.getMessage());
        return response;
    }


    @ExceptionHandler(WrongPasswordException.class)
    @ResponseBody
    public Response processValidationPasswordException(WrongPasswordException ex) {
        Response response = new Response();
        response.setResultCode(12);
        response.setMessage(ex.getMessage());
        return response;
    }
}

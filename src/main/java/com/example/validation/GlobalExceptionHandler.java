package com.example.validation;

import com.example.business.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by zealot on 05.12.2015.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public Response defaultErrorHandler(Exception e) {
        Response response = new Response();
        response.setMessage(e.getMessage());
        response.setResultCode(5);
        return response;
    }
}

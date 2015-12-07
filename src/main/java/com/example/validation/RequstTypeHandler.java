package com.example.validation;

import com.example.business.Response;
import com.example.exceptions.InvalidRequestException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by zealot on 05.12.2015.
 */
@ControllerAdvice
public class RequstTypeHandler {
    @ExceptionHandler(value = InvalidRequestException.class)
    public Response defaultErrorHandler(Exception e) {
        Response response = new Response();
        response.setMessage("Invalid request type");
        response.setResultCode(13);
        return response;
    }
}

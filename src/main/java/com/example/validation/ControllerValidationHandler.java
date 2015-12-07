package com.example.validation;

import com.example.business.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by zealot on 05.12.2015.
 */
@ControllerAdvice
public class ControllerValidationHandler {
    @Autowired
    private MessageSource msgSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        Object value = error.getRejectedValue();
        String msg = error.getDefaultMessage();
        Response response = new Response();
        String field = error.getField();
        if (field.equals("password")) {
        response.setResultCode(3);
        response.setMessage("password " + value + " invalid :" + msg);}
        else {
            response.setResultCode(2);
            response.setMessage("login " + value + " invalid :" + msg);
        }
        return response;
    }
}

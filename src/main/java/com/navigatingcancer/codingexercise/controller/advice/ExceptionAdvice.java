package com.navigatingcancer.codingexercise.controller.advice;

import com.navigatingcancer.codingexercise.model.ErrorMessage;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionAdvice {

    private static final String SERVER_ERROR = "Internal Server Error";

    private static final String ARITHMETIC_ERROR = "There was an arithmetic error, please validate you parameters are not larger than" +
            "or smaller than Integer.MAX_VALUE and Integer.MIN_VALUE respectively";

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorMessage handleClientError(Exception ex) {
        if(ex instanceof ArithmeticException){
            return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ARITHMETIC_ERROR, new Date());
        }

        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), SERVER_ERROR, new Date());
    }
}

package com.example.customer.dao.handling;

import com.example.customer.dao.exception.FoundException;
import com.example.customer.dao.exception.NotFoundException;
import com.example.customer.model.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ResponseStatus(HttpStatus.FOUND)
    @ExceptionHandler(FoundException.class)
    public ExceptionDto foundHandler(RuntimeException exception){
        return new ExceptionDto(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ExceptionDto notFoundHandler(RuntimeException exception){
        return new ExceptionDto(exception.getMessage());
    }
}

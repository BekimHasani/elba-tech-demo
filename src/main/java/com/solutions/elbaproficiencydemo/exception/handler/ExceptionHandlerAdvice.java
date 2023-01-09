package com.solutions.elbaproficiencydemo.exception.handler;

import com.solutions.elbaproficiencydemo.exception.response.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({FileNotFoundException.class})
    public ResponseEntity<HttpException> handleFileNotFoundException(FileNotFoundException ex){
        HttpException response = new HttpException(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}

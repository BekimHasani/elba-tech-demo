package com.solutions.elbaproficiencydemo.exception.response;

import org.springframework.http.HttpStatus;

public class HttpException {
    private HttpStatus httpStatus;
    private String message;

    public HttpException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}

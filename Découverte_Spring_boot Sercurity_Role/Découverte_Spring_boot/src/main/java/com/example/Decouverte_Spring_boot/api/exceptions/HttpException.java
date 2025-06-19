package com.example.Decouverte_Spring_boot.api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final Object errors;

    public HttpException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.errors = null;
    }

    public HttpException(HttpStatus httpStatus, String message, Object errors) {
        super(message);
        this.httpStatus = httpStatus;
        this.errors = errors;
    }

    public int getStatusCode() {
        return httpStatus.value();
    }

    public String getStatusMessage() {
        return httpStatus.getReasonPhrase();
    }
}

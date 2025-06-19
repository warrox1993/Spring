package com.example.Decouverte_Spring_boot.api.advices;

import com.example.Decouverte_Spring_boot.api.models.exceptions.ExceptionDTO;
import com.example.Decouverte_Spring_boot.api.exceptions.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpControllerAdvice {

    @ExceptionHandler({HttpException.class})
    public ResponseEntity<ExceptionDTO> exceptionHandler(HttpException e) {
        ExceptionDTO dto = new ExceptionDTO(e.getStatusCode(), e.getStatusMessage(), e.getMessage(), e.getErrors());
        return ResponseEntity.status(dto.statusCode()).body(dto);
    }
}

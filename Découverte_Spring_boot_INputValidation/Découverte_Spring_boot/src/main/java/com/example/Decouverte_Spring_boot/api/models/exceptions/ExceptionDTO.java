package com.example.Decouverte_Spring_boot.api.models.exceptions;

public record ExceptionDTO(
        int statusCode,
        String statusMessage,
        String message,
        Object errors
) {
}

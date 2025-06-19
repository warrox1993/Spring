package com.example.Decouverte_Spring_boot.api.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BindingResultUtil {

    public static Map<String, List<String>> getErrorsFormBindingResult(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .collect(
                        Collectors.groupingBy(
                                FieldError::getField,
                                Collectors.mapping(
                                        FieldError::getDefaultMessage,
                                        Collectors.toList()
                                )
                        )
                );
    }
}

package com.example.Decouverte_Spring_boot.api.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BindingResultUtil {

    public static Map<String, List<String>> getErrorsFormBindingResult(BindingResult bindingResult) {
        Stream<FieldError> fieldErrorStream = bindingResult.getFieldErrors().stream();
        Stream<ObjectError> objectErrorStream = bindingResult.getGlobalErrors().stream();

        return Stream.concat(objectErrorStream, fieldErrorStream)
                .collect(
                        Collectors.groupingBy(
                                ObjectError::getObjectName,
                                Collectors.mapping(
                                        ObjectError::getDefaultMessage,
                                        Collectors.toList()
                                )
                        )
                );

//        return bindingResult.getFieldErrors().stream()
//                .collect(
//                        Collectors.groupingBy(
//                                FieldError::getField,
//                                Collectors.mapping(
//                                        FieldError::getDefaultMessage,
//                                        Collectors.toList()
//                                )
//                        )
//                );
    }
}

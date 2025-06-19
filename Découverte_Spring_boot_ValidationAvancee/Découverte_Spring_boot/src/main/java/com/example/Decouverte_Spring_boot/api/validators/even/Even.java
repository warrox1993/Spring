package com.example.Decouverte_Spring_boot.api.validators.even;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EvenValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Even {
    String message() default "The number must be even";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

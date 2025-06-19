package com.example.Decouverte_Spring_boot.api.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class EvenValidator implements ConstraintValidator<Even, Integer> {
    private Even constraintAnnotation;

    @Override
    public void initialize(Even constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer % 2 == 0;
    }
}

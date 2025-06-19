package com.example.Decouverte_Spring_boot.api.validators.fieldsValueMatch;

import jakarta.validation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface FieldsValueMatch {
    String message();

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

    String field();

    String fieldMatch();

    FieldValueComparer comparer() default FieldValueComparer.EQUALS;


    @Target({java.lang.annotation.ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }
}

package com.example.Decouverte_Spring_boot.api.validators.fieldsValueMatch;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {
    private String[] fields;
    private String[] fieldsMatch;
    private FieldValueComparer comparer;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.fields = constraintAnnotation.field().split("\\.");
        this.fieldsMatch = constraintAnnotation.fieldMatch().split("\\.");
        this.comparer = constraintAnnotation.comparer();
    }

    @Override
    @SneakyThrows
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Comparable<Object> fieldValue = getComparable(o, fields);
        Comparable<Object> fieldValueMatch = getComparable(o, fieldsMatch);

        return switch (comparer) {
            case EQUALS -> fieldValue.equals(fieldValueMatch);
            case NOT_EQUALS -> !fieldValue.equals(fieldValueMatch);
            case GT -> fieldValue.compareTo(fieldValueMatch) > 0;
            case GTE -> fieldValue.compareTo(fieldValueMatch) >= 0;
            case LT -> fieldValue.compareTo(fieldValueMatch) < 0;
            case LTE -> fieldValue.compareTo(fieldValueMatch) <= 0;
        };
    }

    @SuppressWarnings("unchecked")
    private Comparable<Object> getComparable(Object o, String[] path) throws NoSuchFieldException, IllegalAccessException {
        if (path.length == 0) throw new NoSuchFieldException("path is empty");

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(o);
        Object value = beanWrapper.getPropertyValue(path[0]);
        if (value == null) throw new IllegalAccessException("value is null");
        Class<?> vClass = value.getClass();

        for (int i = 1; i < path.length; i++) {
            Field field = vClass.getDeclaredField(path[i]);
            field.setAccessible(true);
            value = field.get(value);
        }

        if (!(value instanceof Comparable))
            throw new IllegalAccessException("FieldsValueMatchValidator: value is not comparable");

        return (Comparable<Object>) value;
    }
}

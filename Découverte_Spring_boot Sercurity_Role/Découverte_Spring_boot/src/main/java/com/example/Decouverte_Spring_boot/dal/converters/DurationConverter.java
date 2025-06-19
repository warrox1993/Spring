package com.example.Decouverte_Spring_boot.dal.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Duration;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {
    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        if (duration == null) return -1L;
        return duration.toNanos();
    }

    @Override
    public Duration convertToEntityAttribute(Long aLong) {
        return Duration.ofNanos(aLong);
    }
}

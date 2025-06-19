package com.example.Decouverte_Spring_boot.api.models.plane;

import com.example.Decouverte_Spring_boot.api.validators.Even;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

public record PlaneCreateForm(
        @NotBlank(message = "Connard il manque l'immatriculation") String imma,
        @NotBlank String ownerName,
        @NotNull @NotEmpty String typeName,
        @Even(defaultMessage = "Event") Integer numberOfSeats
) {
}

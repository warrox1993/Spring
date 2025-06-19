package com.example.Decouverte_Spring_boot.api.models.plane;

import com.example.Decouverte_Spring_boot.api.validators.even.Even;
import com.example.Decouverte_Spring_boot.api.validators.fieldsValueMatch.FieldValueComparer;
import com.example.Decouverte_Spring_boot.api.validators.fieldsValueMatch.FieldsValueMatch;
import jakarta.validation.constraints.*;

@FieldsValueMatch.List({
        @FieldsValueMatch(
                message = "type.minSeats must be greater than numberOfSeats",
                field = "type.minSeats", fieldMatch = "numberOfSeats",
                comparer = FieldValueComparer.GT
        ),
        @FieldsValueMatch(
                message = "Owner name not equals to typeName",
                field = "ownerName",
                fieldMatch = "typeName"
        )
})
public record PlaneCreateForm(
        @NotBlank(message = "The imma field is a mandatory") String imma,
        @NotBlank String ownerName,
        @NotBlank String typeName,
        @NotNull PlaneType type,
        @Even(message = "Number of seats must be even") Integer numberOfSeats
) {
}

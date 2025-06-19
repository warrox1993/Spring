package com.example.Decouverte_Spring_boot.api.models.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.checkerframework.common.value.qual.MinLen;

public record SignInForm(
        @NotBlank @Email String email,
        @NotBlank @MinLen(5) String password
) {
}

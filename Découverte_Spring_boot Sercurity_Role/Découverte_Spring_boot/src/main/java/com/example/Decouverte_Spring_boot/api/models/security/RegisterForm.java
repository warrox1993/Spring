package com.example.Decouverte_Spring_boot.api.models.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterForm(
        @NotBlank @Email String email,
        @NotBlank String password
) {
}

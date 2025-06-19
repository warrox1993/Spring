package com.example.Decouverte_Spring_boot.api.models.security;

public record UserTokenDTO(
        String accessToken,
        UserDTO user
) {
}

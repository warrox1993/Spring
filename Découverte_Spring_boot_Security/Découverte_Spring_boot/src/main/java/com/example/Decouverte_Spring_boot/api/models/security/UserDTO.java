package com.example.Decouverte_Spring_boot.api.models.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public record UserDTO(
        String email,
        List<String> roles
) {

    public static UserDTO fromDetails(UserDetails userDetails) {
        return new UserDTO(userDetails.getUsername(), userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    }
}

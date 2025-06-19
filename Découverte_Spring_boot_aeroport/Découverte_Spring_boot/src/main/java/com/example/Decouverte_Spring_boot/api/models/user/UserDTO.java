package com.example.Decouverte_Spring_boot.api.models.user;

import com.example.Decouverte_Spring_boot.dal.domain.entities.User;

public record UserDTO(
        Long id,
        String nom,
        String email
) {

    public UserDTO fromDAL(User user) {
        return new UserDTO(user.getId(), user.getNom(), user.getEmail());
    }
}

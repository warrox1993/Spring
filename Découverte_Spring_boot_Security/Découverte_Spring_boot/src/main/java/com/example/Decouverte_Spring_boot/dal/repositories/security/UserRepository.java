package com.example.Decouverte_Spring_boot.dal.repositories.security;

import com.example.Decouverte_Spring_boot.dal.entities.security.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<UserEntity> findByEmail(String email);
}

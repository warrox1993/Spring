package com.example.Decouverte_Spring_boot.dal.repositories.security;

import com.example.Decouverte_Spring_boot.dal.entities.security.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);

    @Query("SELECT r FROM Role r WHERE r.name IN (:names)")
    List<RoleEntity> findAllByNames(List<String> names);
}

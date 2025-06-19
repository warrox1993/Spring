package com.example.Decouverte_Spring_boot.dal.repositories;

import com.example.Decouverte_Spring_boot.dal.entities.PlaneTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaneTypeRepository extends JpaRepository<PlaneTypeEntity, Long> {
    @Query("select pt from PlaneType pt where pt.name = :name")
    Optional<PlaneTypeEntity> findOneByName(String name);
}

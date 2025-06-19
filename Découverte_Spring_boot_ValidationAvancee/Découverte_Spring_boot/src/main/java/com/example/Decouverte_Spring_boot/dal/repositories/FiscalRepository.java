package com.example.Decouverte_Spring_boot.dal.repositories;

import com.example.Decouverte_Spring_boot.dal.entities.fiscals.FiscalEntity;
import com.example.Decouverte_Spring_boot.dal.entities.fiscals.FiscalEntity_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FiscalRepository extends JpaRepository<FiscalEntity, Long> {
    @Query("SELECT f FROM Fiscal f where f.name = :name")
    Optional<FiscalEntity> findOneByName(@Param("name") String name);
}

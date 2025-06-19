package com.example.Decouverte_Spring_boot.dal.repositories;

import com.example.Decouverte_Spring_boot.dal.entities.PlaneEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaneRepository extends JpaRepository<PlaneEntity, Long>, JpaSpecificationExecutor<PlaneEntity> {
    @Query("SELECT p FROM Plane p where p.active = true")
    List<PlaneEntity> findAll();

    @Query("SELECT p FROM Plane p where p.imma ilike :imma")
    List<PlaneEntity> findAllByImmaLike(String imma);

    @Modifying
    @Query("UPDATE Plane p SET p.imma = :imma WHERE p.id = :id")
    void updateImmaById(Long id, String imma);

    static Specification<PlaneEntity> getByImma(String imma) {
        return (root, query, cb) -> cb.like(root.get("imma"), "%" + imma + "%");
    }
}

package com.example.Decouverte_Spring_boot.dal.repositories;

import com.example.Decouverte_Spring_boot.dal.entities.PlaneEntity;
import com.example.Decouverte_Spring_boot.dal.entities.PlaneTypeEntity;
import com.example.Decouverte_Spring_boot.dal.entities.fiscals.FiscalEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Modifying
    @Query("update Plane set active = false where id = :id")
    void deleteById(@Param("id") Long id);

    @Modifying
    @Query("update Plane set active = false where imma = :imma")
    void deleteByImma(String imma);


    static Join<PlaneEntity, FiscalEntity> joinOwner(Root<PlaneEntity> root) {
        return joinOwner(root, JoinType.INNER);
    }

    static Join<PlaneEntity, FiscalEntity> joinOwner(Root<PlaneEntity> root, JoinType joinType) {
        return root.join("owner", joinType);
    }

    static Join<PlaneEntity, PlaneTypeEntity> joinPlaneType(Root<PlaneEntity> roots) {
        return joinPlaneType(roots, JoinType.INNER);
    }

    static Join<PlaneEntity, PlaneTypeEntity> joinPlaneType(Root<PlaneEntity> root, JoinType joinType) {
        return root.join("type", joinType);
    }

    static Specification<PlaneEntity> withOwnerByName(String name) {
        return (root, query, builder) -> {
            var ownerJoined = joinOwner(root);

            return builder.equal(ownerJoined.get("name"), name);
        };
    }

    static Specification<PlaneEntity> withPlaneTypeByName(String name) {
        return (root, query, criteriaBuilder) -> {
            var planeTypeJoined = joinPlaneType(root);

            return criteriaBuilder.equal(planeTypeJoined.get("name"), name);
        };
    }

    static Specification<PlaneEntity> byId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    static Specification<PlaneEntity> byImma(String imma) {
        return (root, query, cb) -> cb.like(root.get("imma"), "%" + imma + "%");
    }

    static Specification<PlaneEntity> byActive(boolean active) {
        return (root, query, cb) -> cb.equal(root.get("active"), active);
    }
}

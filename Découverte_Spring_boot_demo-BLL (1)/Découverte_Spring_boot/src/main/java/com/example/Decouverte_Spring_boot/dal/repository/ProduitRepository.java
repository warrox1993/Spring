package com.example.Decouverte_Spring_boot.dal.repository;

import com.example.Decouverte_Spring_boot.dal.domain.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    @Modifying
    @Query("UPDATE Produit SET prix = :price WHERE id = :id")
    Optional<Produit> updatePrix(Long id, Double price);
}

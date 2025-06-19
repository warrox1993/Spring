package com.example.Decouverte_Spring_boot.dal.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prix")
    private Double prix;

    @Column
    private boolean isPromote = false;


    /**
     * Le constructeur vide est le constructeur par défaut REQUIS par JPA.
     */
    public Produit() {
    }

    /**
     * Notre constructeur pratique
     */
    public Produit(String nom, Double prix) {
        this.nom = nom;
        this.prix = prix;
    }


}

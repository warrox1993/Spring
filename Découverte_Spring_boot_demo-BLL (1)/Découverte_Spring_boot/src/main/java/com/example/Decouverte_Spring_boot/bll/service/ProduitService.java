package com.example.Decouverte_Spring_boot.bll.service;

import com.example.Decouverte_Spring_boot.dal.domain.entities.Produit;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ProduitService {
    Produit update(Long id, String nom, Boolean isPromoted);
}

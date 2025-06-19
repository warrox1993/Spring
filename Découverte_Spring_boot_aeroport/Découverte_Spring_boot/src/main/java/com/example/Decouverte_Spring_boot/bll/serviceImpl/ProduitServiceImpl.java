package com.example.Decouverte_Spring_boot.bll.serviceImpl;

import com.example.Decouverte_Spring_boot.bll.service.ProduitService;
import com.example.Decouverte_Spring_boot.dal.domain.entities.Produit;
import com.example.Decouverte_Spring_boot.dal.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService {
    private final ProduitRepository produitRepository;

    @Override
    public Produit update(Long id, String nom, Boolean isPromoted) {
        Produit p = produitRepository.findById(id).orElseThrow();

        if (nom != null)
            p.setNom(nom);
        if (isPromoted != null)
            p.setPromote(isPromoted);

        return this.produitRepository.save(p);
    }
}

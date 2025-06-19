package com.example.Decouverte_Spring_boot.api.controller;

import com.example.Decouverte_Spring_boot.api.models.product.ProductUpdateForm;
import com.example.Decouverte_Spring_boot.bll.service.ProduitService;
import com.example.Decouverte_Spring_boot.dal.domain.entities.Produit;
import com.example.Decouverte_Spring_boot.dal.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Imagions que je développe un crud sur produit :
 * <p>
 * create : http://localhost/8080/produit/create
 * read : http://localhost/8080/produit/read
 * update : http://localhost/8080/produit/update
 * delete  : http://localhost/8080/produit/delete
 * <p>
 * Qu'est-ce qu'on ne supporte pas nous, développeurs ?
 * La répétition donc, via request mapping, je peux spécifier, ajoute à chaque route une base.
 * <p>
 * Si je spécifie produit dans requestMapping :
 * <p>
 * create : create
 * read : ""
 * update : ""
 * delete  : delete
 * <p>
 * => spring va faire avec tout ça => ce qui se trouve dans RequestMapping + l'url du enpoint => produit + /create
 */
@RestController
@RequestMapping("produit")
@RequiredArgsConstructor
public class ProduitController {
    private final ProduitService produitService;
    private final ProduitRepository produitRepository;
//
//    public ProduitController(ProduitRepository repo) {
//        this.repo = repo;
//    }

    @GetMapping
    public List<Produit> all() {
        return produitRepository.findAll();
    }

    //http://localhost:8080/user/1

    /**
     * Quand j'appuie sur mon login en front-end, j'envoie une requête à mon back (api), et si tout se passe bien,
     * je renvoie une réponse.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getOne(@PathVariable Long id) {
        return produitRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //http://localhost:8080/user
    @PostMapping
    public ResponseEntity<Produit> create(@RequestBody Produit p) {
        return ResponseEntity.ok(
                produitRepository.save(p)  // enregistre en db
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> update(@PathVariable Long id, @RequestBody ProductUpdateForm nouveauProduit) {
//        return produitRepository.findById(id)
//                .map( (produitActuel) -> {
//                    produitActuel.setNom( nouveauProduit.getName() );
//                    produitActuel.setPromote(nouveauProduit.getIsPromote());
//                    return ResponseEntity.ok( produitRepository.save( produitActuel) );
//                }).orElse(ResponseEntity.notFound().build());
        try {
            Produit p = produitService.update(id, nouveauProduit.getName(), nouveauProduit.getIsPromote());
            return ResponseEntity.ok(p);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!produitRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produitRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

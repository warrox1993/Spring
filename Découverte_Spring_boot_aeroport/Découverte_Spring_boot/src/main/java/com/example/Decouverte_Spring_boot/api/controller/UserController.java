package com.example.Decouverte_Spring_boot.api.controller;

import com.example.Decouverte_Spring_boot.dal.domain.entities.User;
import com.example.Decouverte_Spring_boot.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok) // Retourne 200 OK si trouvé
                .orElseGet(() -> ResponseEntity.notFound().build()); // Retourne 404 si non trouvé
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User u) {
        return ResponseEntity.ok(
                userRepository.save(u)  // enregistre en db
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User nouveauUser) {
        return userRepository.findById(id)
                .map((userActuel) -> {
                    userActuel.setNom(nouveauUser.getNom());
                    return ResponseEntity.ok(userRepository.save(userActuel));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
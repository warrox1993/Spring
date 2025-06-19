package com.example.Decouverte_Spring_boot.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Là j'instancie mon controller dans mon contexte Spring.
 */
@RestController
public class HelloWorldController {

    /**
     * Là, je crée un endpoint.
     *
     * @return
     */
    @GetMapping("/Hello") //GET POST PUT PATCH DELETE
    public String seyHello() {
        return "Hello";
    }
}

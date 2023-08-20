package com.deveficiente.rinhadevbackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;

@RestController
public class ContagemPessoasController {
    
    private EntityManager entityManager;

    public ContagemPessoasController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @GetMapping("/contagem-pessoas")
    public int contaPessoas() {
        return entityManager.createQuery("select count(p) from Pessoa p", Long.class)
                .getSingleResult()
                .intValue();
    }
}

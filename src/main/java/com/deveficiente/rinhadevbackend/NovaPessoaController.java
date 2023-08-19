package com.deveficiente.rinhadevbackend;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
public class NovaPessoaController {

    private final EntityManager entityManager;

    public NovaPessoaController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @PostMapping("/pessoas")
    @Transactional
    public ResponseEntity<?> novaPessoa(@Valid @RequestBody NovaPessoaRequest request) {
        System.out.println(request);
        Pessoa novaPessoa = request.toModel();
        entityManager.persist(novaPessoa);
        return ResponseEntity.created(URI.create("/pessoas/"+novaPessoa.getCodigo())).build();
    }
}

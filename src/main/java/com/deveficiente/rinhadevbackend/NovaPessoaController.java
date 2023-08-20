package com.deveficiente.rinhadevbackend;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

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
    private final AsyncExecutor asyncExecutor;
    private final TransactionRunner transactionRunner;

    public NovaPessoaController(EntityManager entityManager, AsyncExecutor asyncExecutor, TransactionRunner transactionRunner) {
        this.entityManager = entityManager;
        this.asyncExecutor = asyncExecutor;
        this.transactionRunner = transactionRunner;
    }
    
    @PostMapping("/pessoas")
    public CompletableFuture<ResponseEntity<?>> novaPessoa(@Valid @RequestBody NovaPessoaRequest request) {
        Pessoa novaPessoa = request.toModel();

        return asyncExecutor.execute(() -> {
            transactionRunner.execute(() -> entityManager.persist(novaPessoa));
            return novaPessoa;
        }).thenApply(pessoaGravadaNoBanco -> {
            return ResponseEntity.created(URI.create("/pessoas/"+pessoaGravadaNoBanco.getCodigo())).build();
        });

        
    }
}

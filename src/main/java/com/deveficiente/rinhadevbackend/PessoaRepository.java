package com.deveficiente.rinhadevbackend;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    public Optional<Pessoa> findByCodigo(UUID codigo);
}

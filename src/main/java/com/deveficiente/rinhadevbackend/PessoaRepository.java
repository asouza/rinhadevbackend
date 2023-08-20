package com.deveficiente.rinhadevbackend;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    public Optional<Pessoa> findByCodigo(UUID codigo);

    @Query("select p from Pessoa p where p.pesquisaRapida like %:t%")
    public Page<Pessoa> pesquisaEmTudo(@Param("t") String termo, PageRequest paginacao);
}

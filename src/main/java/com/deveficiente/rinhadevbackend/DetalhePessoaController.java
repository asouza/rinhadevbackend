package com.deveficiente.rinhadevbackend;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DetalhePessoaController {

    private PessoaRepository pessoaRepository;

    public DetalhePessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
    
    @GetMapping("/pessoas/{codigo}")
    public DetalhePessoaResponse detalhePessoa(@PathVariable("codigo") UUID codigo) {

        return pessoaRepository.findByCodigo(codigo)
                .map(DetalhePessoaResponse::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
    }

}

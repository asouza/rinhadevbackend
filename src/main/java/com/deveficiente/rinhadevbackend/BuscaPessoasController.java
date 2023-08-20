package com.deveficiente.rinhadevbackend;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuscaPessoasController {

    private PessoaRepository pessoaRepository;

    public BuscaPessoasController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
    
    @GetMapping("/pessoas")
    public List<DetalhePessoaResponse> busca(@RequestParam String t){
        Page<Pessoa> pessoas = pessoaRepository.pesquisaEmTudo(t,PageRequest.of(0, 50));        
        List<DetalhePessoaResponse> resultado = pessoas.map(DetalhePessoaResponse::new).toList();
        return resultado;
    }
    
}

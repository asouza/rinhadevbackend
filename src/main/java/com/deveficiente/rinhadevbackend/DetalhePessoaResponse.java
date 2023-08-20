package com.deveficiente.rinhadevbackend;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class DetalhePessoaResponse {

    public final String apelido;
    public final String nascimento;
    public final List<String> stack;
    public final String nome;
    public final UUID id;

    public DetalhePessoaResponse(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.id = pessoa.getCodigo();
        this.apelido = pessoa.getApelido();
        this.nascimento = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(pessoa.getNascimento());
        this.stack = pessoa.getStack();
    }

    

}

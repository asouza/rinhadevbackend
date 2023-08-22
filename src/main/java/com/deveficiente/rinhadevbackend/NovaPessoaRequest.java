package com.deveficiente.rinhadevbackend;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

/*
 * {
    "apelido" : "josé",
    "nome" : "José Roberto",
    "nascimento" : "2000-10-01",
    "stack" : ["C#", "Node", "Oracle"]
}
 */
public class NovaPessoaRequest {

    @NotBlank
    @Size(max = 32)
    private String apelido;
    @NotBlank
    @Size(max = 100)
    private String nome;
    @NotNull
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate nascimento;
    private List<@NotBlank @Size(max = 32) String> stack;

    public NovaPessoaRequest(@NotBlank String apelido, @NotBlank String nome, @NotNull @Past LocalDate nascimento) {
        super();
        this.apelido = apelido;
        this.nome = nome;
        this.nascimento = nascimento;                
    }

    //investigar pq eu preciso do getter aqui para ele continuar o fluxo de validacao
    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }

    @Override
    public String toString() {
        return "NovaPessoaRequest [apelido=" + apelido + ", nome=" + nome + ", nascimento=" + nascimento + ", stack="
                + stack + "]";
    }

    public Pessoa toModel() {
        Pessoa novaPessoa = new Pessoa(apelido, nome, nascimento);

        //como a stack não é obrigatoria não passei pelo construtor
        if(stack != null && !stack.isEmpty()) {
            novaPessoa.setStack(stack);
        }
        return novaPessoa;
    }

    

}

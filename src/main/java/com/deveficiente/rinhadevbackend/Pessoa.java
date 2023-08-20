package com.deveficiente.rinhadevbackend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.IndexColumn;
import org.springframework.util.Assert;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
public class Pessoa {

    //no caso aqui do desafio vou deixar o hibernate criar a tabela em funcao do que ta aqui. 

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Size(max = 32)
    private @NotBlank String apelido;
    @Size(max = 100)
    @Column(unique = true)
    //precisa criar o handler aqui para tratar a excecao de violacao de unicidade
    private @NotBlank String nome;
    private @NotNull @Past LocalDate nascimento;
    //validacao que só pode aceitar palavras
    @ElementCollection
    private List<@NotBlank @Size(max = 32) @EhUmaPalavra String> stack = new ArrayList<>();
    @NotNull
    private UUID codigo = UUID.randomUUID();    
    private String pesquisaRapida;

    @Deprecated
    public Pessoa() {
    }

    public Pessoa(@NotBlank String apelido, @NotBlank String nome, @NotNull @Past LocalDate nascimento) {
                this.apelido = apelido;
                this.nome = nome;
                this.nascimento = nascimento;   
                this.pesquisaRapida = this.apelido.concat(this.nome);             
    }

    public void setStack(@NotNull List<@NotBlank String> stack) {
        Assert.notNull(stack, "A lista de stack não pode ser nula");
        Assert.notEmpty(stack, "A lista de stack não pode ser vazia");
        //relaxando a validacao de apenas numeros aqui e também de tamanho        
        this.stack = stack;
        this.pesquisaRapida = this.pesquisaRapida.concat(this.stack
            .stream()
            .reduce("", (a, b) -> a.concat(b)));
    }

    public UUID getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getApelido() {
        return apelido;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public List<String> getStack() {
        return stack;
    }

}

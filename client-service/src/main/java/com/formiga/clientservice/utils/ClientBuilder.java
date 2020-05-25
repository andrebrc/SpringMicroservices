package com.formiga.clientservice.utils;

import com.formiga.clientservice.model.Client;
import com.formiga.clientservice.model.SexoEnum;
import java.time.LocalDate;

public class ClientBuilder {


    private Long id;
    private String nome = "";
    private SexoEnum sexo = null;
    private LocalDate dataNascimento;
    private Long idCidade;


    public ClientBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ClientBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClientBuilder setSexo(SexoEnum sexo) {
        this.sexo = sexo;
        return this;
    }

    public ClientBuilder setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public ClientBuilder setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
        return this;
    }

    public Client build() {
        return new Client(nome, sexo, this.dataNascimento, this.idCidade);
    }

}

package com.formiga.cidadeservice.utils;

import com.formiga.cidadeservice.model.Cidade;
import com.formiga.cidadeservice.model.UF;


public class CidadeBuilder {

    private Long id;
    private String nome = "";
    private UF uf;


    public CidadeBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public CidadeBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public CidadeBuilder setUf(UF uf) {
        this.uf = uf;
        return this;
    }

    public Cidade build() {
        return new Cidade(this.nome, this.uf);
    }

}

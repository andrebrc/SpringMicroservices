package com.formiga.cidadeservice.model;

import javax.persistence.*;

@Entity
public class Cidade {


    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    @Enumerated(EnumType.ORDINAL)
    private UF uf;

    public Cidade() {
    }

    public Cidade(String nome, UF uf) {
        this.nome = nome;
        this.uf = uf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}

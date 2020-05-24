package com.formiga.cidadeservice.repository;

import com.formiga.cidadeservice.model.Cidade;
import com.formiga.cidadeservice.model.UF;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CidadeRepository extends CrudRepository<Cidade,  Long> {
    List<Cidade> findByNomeOrUf(String nome, UF uf);
}


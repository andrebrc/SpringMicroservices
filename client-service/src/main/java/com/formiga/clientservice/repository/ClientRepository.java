package com.formiga.clientservice.repository;

import com.formiga.clientservice.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Optional<Client> findById(Long id);

    List<Client> findByNome(String nome);
}


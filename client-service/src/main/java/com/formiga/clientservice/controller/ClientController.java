package com.formiga.clientservice.controller;

import com.formiga.clientservice.model.Client;
import com.formiga.clientservice.repository.ClientRepository;
import com.formiga.clientservice.utils.ClientNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class ClientController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClientRepository repository;

    @GetMapping("/clients/{id}")
    public Client findById(@PathVariable Long id) {
        Client client = repository.findById(id).get();
        log.info("Nome: " + client.getNome());
        return client;
    }


    @GetMapping("/clients")
    public Client findByName(@RequestParam(name = "nome", required = true) String nome) {
        Client byNome = repository.findByNome(nome).get();
        log.info("Nome: " + byNome.getNome());
       return byNome;
    }


    @PostMapping("/clients")
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        log.info("Client to save:" + client.toString());
        Client savedClient = repository.save(client);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedClient.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/clients/{id}")
    public void deleteUser(@PathVariable long id) {
        Optional<Client> byId = repository.findById(id);
        if (byId.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ClientNotFoundException("id: " + id);
        }
    }

    @PatchMapping("/clients/{id}")
    public ResponseEntity<?> partialUpdateName(
            @RequestBody Client client, @PathVariable Long id
    ) {
        Optional<Client> byId = repository.findById(id);
        if(byId.isPresent()) {
            Client currentClient = byId.get();
            currentClient.setNome(client.getNome());
            Client updatedClient = repository.save(currentClient);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(updatedClient.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else {
            throw new ClientNotFoundException("id: " + id);
        }
    }

}

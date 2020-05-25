package com.formiga.clientservice.controller;

import com.formiga.clientservice.model.Cidade;
import com.formiga.clientservice.model.Client;
import com.formiga.clientservice.repository.ClientRepository;
import com.formiga.clientservice.utils.ClientNotFoundException;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ClientController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CidadeServiceProxy cidadeService;

    @Autowired
    private ClientRepository repository;

    @GetMapping("/test")
    public String teste() {

        Cidade cidades = cidadeService.findById(1L);
        log.info(String.valueOf(cidades));

        String result = this.restTemplate.getForObject("http://CIDADE-SERVICE/cidades/1", String.class);
        return result;
    }

    @GetMapping("/clients/{id}")
    public Client findById(@PathVariable Long id) {
        Optional<Client> byId = repository.findById(id);
        if (byId.isPresent()) {
            Client client = byId.get();
            Cidade cidade = cidadeService.findById(client.getIdCidade());
            client.setCidade(cidade);
            return client;
        } else {
            throw new ClientNotFoundException("id: " + id);
        }
    }


    @GetMapping("/clients")
    public List<Client> findByName(@RequestParam(name = "nome", required = true) String nome) {
        List<Client> byNome = repository.findByNome(nome);
        if (!byNome.isEmpty()) {
            return byNome.stream().map(i -> {
                Cidade cidade = cidadeService.findById(i.getIdCidade());
                i.setCidade(cidade);
                return i;
            }).collect(Collectors.toList());
        } else {
            throw new ClientNotFoundException("Nome: " + nome);
        }
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

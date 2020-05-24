package com.formiga.cidadeservice.controller;

import com.formiga.cidadeservice.model.Cidade;
import com.formiga.cidadeservice.model.UF;
import com.formiga.cidadeservice.repository.CidadeRepository;
import com.formiga.cidadeservice.utils.CidadeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
public class CidadeController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${port}")
    private String port;

    @Autowired
    private CidadeRepository repository;

    @RequestMapping("/port")
    public String port() {
        return this.port;
    }

    @PostMapping("/cidades")
    public ResponseEntity<?> createClient(@RequestBody Cidade cidade) {
        log.info("Client to save:" + cidade.toString());
        Cidade savedCity = repository.save(cidade);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCity.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/cidades/{id}")
    public Cidade findById(@PathVariable Long id) {
        Optional<Cidade> cidadePorId = repository.findById(id);
        if(cidadePorId.isPresent() ) {
            return cidadePorId.get();
        }
        throw new CidadeNotFoundException("id- " + id);
    }

    @GetMapping("/cidades")
    public List<Cidade> findByName(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "uf", required = false) UF uf) {
        return repository.findByNomeOrUf(nome, uf);
    }
}

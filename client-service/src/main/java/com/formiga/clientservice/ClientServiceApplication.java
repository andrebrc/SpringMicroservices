package com.formiga.clientservice;

import com.formiga.clientservice.model.Cidade;
import com.formiga.clientservice.model.Client;
import com.formiga.clientservice.model.SexoEnum;
import com.formiga.clientservice.repository.ClientRepository;
import com.formiga.clientservice.utils.ClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ClientServiceApplication {

    private static final Logger log =
            LoggerFactory.getLogger(ClientServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner setInitialData(ClientRepository repository) {
//        return (args) -> {
//            log.info("Save client pedro");
//            Client pedro = new ClientBuilder()
//                    .setNome("Pedro")
//                    .setSexo(SexoEnum.MASCULINO)
//                    .setDataNascimento(LocalDate.now())
//                    .setIdade(20)
//                    .setCidade(new Cidade())
//                    .build();
//
//            Client joao = new ClientBuilder()
//                    .setNome("João")
//                    .setIdade(50)
//                    .setSexo(SexoEnum.MASCULINO)
//                    .setDataNascimento(LocalDate.now())
//                    .setCidade(null).build();
//            log.info("Client pedro saved");
//            List<Client> clients = Arrays.asList(pedro, joao);
//
//            repository.saveAll(clients);
//
//            Optional<Client> pedroResult = repository.findByNome("Pedro");
//            log.info("FindByName: " + pedroResult.get().getNome());
//
//            Optional<Client> byId = repository.findById(1L);
//            log.info("FindById: " + pedroResult.get().getId());
//
//            Iterable<Client> all = repository.findAll();
//
//            all.forEach(i -> {
//                log.info("id: " + i.getId());
//                log.info("name: " + i.getNome());
//            });
//        };
//    }
}

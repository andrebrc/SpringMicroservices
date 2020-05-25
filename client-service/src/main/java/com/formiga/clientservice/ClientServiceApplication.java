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
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableDiscoveryClient
public class ClientServiceApplication {

    private static final Logger log =
            LoggerFactory.getLogger(ClientServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public CommandLineRunner setInitialData(ClientRepository repository) {
//        return (args) -> {
//            log.info("Save client pedro");
//            Client pedro = new ClientBuilder()
//                    .setNome("Pedro")
//                    .setSexo(SexoEnum.MASCULINO)
//                    .setDataNascimento(LocalDate.now())
//                    .setIdCidade(1L)
//                    .build();
//
//            Client joao = new ClientBuilder()
//                    .setNome("João")
//                    .setSexo(SexoEnum.MASCULINO)
//                    .setDataNascimento(LocalDate.now())
//                    .setIdCidade(2L).build();
//            log.info("Client pedro saved");
//            List<Client> clients = Arrays.asList(pedro, joao);
//
//            repository.saveAll(clients);
//
//            List<Client> pedrosResult = repository.findByNome("Pedro");
//            log.info("FindByName: " + pedrosResult.toString());
//
//            Optional<Client> byId = repository.findById(1L);
//            log.info("FindById: " + byId.get().getId());
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

package com.formiga.cidadeservice;

import com.formiga.cidadeservice.model.Cidade;
import com.formiga.cidadeservice.model.UF;
import com.formiga.cidadeservice.repository.CidadeRepository;
import com.formiga.cidadeservice.utils.CidadeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class CidadeServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(CidadeServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CidadeServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner setInitialData(CidadeRepository repository) {
		return args -> {

		    log.info("Save Cidade Example");
			Cidade acailandia = new CidadeBuilder()
					.setNome("Açailândia")
					.setUf(UF.MA)
					.build();

			Cidade imperatriz = new CidadeBuilder()
					.setNome("Belo Horizonte")
					.setUf(UF.MA)
					.build();

			repository.save(acailandia);
			repository.save(imperatriz);
			List<Cidade> cidadesMA = repository.findByNomeOrUf(null, UF.MA);

			log.info("FindByUf: " + cidadesMA);

			List<Cidade> imperatrizResult = repository.findByNomeOrUf("Belo Horizonte", null);

			log.info("FindByName: " + imperatrizResult);

		};
	}

}

package com.formiga.clientservice.repository;

import com.formiga.clientservice.model.Client;
import com.formiga.clientservice.utils.ClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ClientRepository repository;

    @Test
    public void it_should_save_client() {
        Client client = new ClientBuilder()
                .setNome("ClientToPersist")
                .build();
        client = entityManager.persistAndFlush(client);
        assertThat(repository.findById(client.getId()).get()).isEqualTo(client);
    }
}
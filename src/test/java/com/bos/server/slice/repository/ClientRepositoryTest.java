package com.bos.server.slice.repository;

import com.bos.server.config.DatabaseConfig;
import com.bos.server.oauth.model.entity.Client;
import com.bos.server.oauth.repository.client.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application_db.yml")
@Import({DatabaseConfig.class})
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("find by id - ok")
    void findById() {
        Optional<Client> byId = clientRepository.findById("1");
        assertThat(byId).isNotNull();
        assertThat(byId.isPresent()).isTrue();
        assertThat(byId.get().getClientId()).isEqualTo("bluebird-test-client");
        assertThat(byId.get().getClientSecret()).isEqualTo("bluebird-test-client-secret");
        assertThat(byId.get().getClientName()).isEqualTo("bluebird-test-client-name");
    }
}

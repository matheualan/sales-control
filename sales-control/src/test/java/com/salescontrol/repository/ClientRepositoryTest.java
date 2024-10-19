package com.salescontrol.repository;

import com.salescontrol.creator.ClientCreator;
import com.salescontrol.model.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests for repository of client")
class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    @Test
    @DisplayName("Save client when successful")
    void saveClient_WhenSuccessful() {
        Client clientSaved = clientRepository.save(ClientCreator.createValidClient());

        Assertions.assertThat(clientSaved).isNotNull();
        Assertions.assertThat(clientSaved.getName()).isEqualTo("Cliente criado com sucesso");
    }

}
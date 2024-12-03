package com.salescontrol.repository;

import com.salescontrol.creator.ClientCreator;
import com.salescontrol.model.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for repository of client")
class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    @Test
    @DisplayName("Save client when successful")
    void saveClient_WhenSuccessful() {
        Client validClient = ClientCreator.createValidClient();
        Client clientSaved = clientRepository.save(validClient);

        Assertions.assertThat(clientSaved).isNotNull();
        Assertions.assertThat(clientSaved.getName()).isEqualTo(validClient.getName());
    }

    @Test
    @DisplayName("Save a list clients when successful")
    void saveListClient_WhenSuccessful() {
        List<Client> clientList = new ArrayList<>();
        clientList.add(ClientCreator.createValidClient());
        clientList.add(ClientCreator.createValidClient2());

        List<Client> clients = clientRepository.saveAll(clientList);

        Assertions.assertThat(clients).isNotNull().isNotEmpty();
        Assertions.assertThat(clients).hasSize(2);
        Assertions.assertThat(clients.get(0).getIdClient()).isNotNull();
        Assertions.assertThat(clients.get(0).getName()).isEqualTo(clientList.get(0).getName());
        Assertions.assertThat(clients.get(1).getIdClient()).isNotNull();
        Assertions.assertThat(clients.get(1).getName()).isEqualTo(clientList.get(1).getName());
        Assertions.assertThat(clients).isNotNull().contains(clientList.get(0))
                .contains(clientList.get(1));
    }

    @Test
    @DisplayName("Return list of clients when successful")
    void returnListOfClients_WhenSuccessful() {
        List<Client> clientList = List.of(ClientCreator.createValidClient(),
                ClientCreator.createValidClient2());

        clientRepository.saveAll(clientList);

        List<Client> listClients = clientRepository.findAll();

        Assertions.assertThat(listClients).isNotNull()
                .isNotEmpty()
                .contains(clientList.get(0))
                .contains(clientList.get(1));
    }

    @Test
    @DisplayName("Return client by name when successful")
    void returnClientByName_WhenSuccessful() {
        List<Client> clientList = List.of(ClientCreator.createValidClient(),
                ClientCreator.createValidClient2());

        clientRepository.saveAll(clientList);

        Client client = clientRepository.findByName(clientList.get(0).getName()).get();

        Assertions.assertThat(client).isNotNull();
        Assertions.assertThat(client.getIdClient()).isNotNull();
        Assertions.assertThat(client.getName()).isEqualTo(clientList.get(0).getName());
    }

    @Test
    @DisplayName("Return client by nickname when successful")
    void returnClientByNickname_WhenSuccessful() {
        Client clientSaved = clientRepository.save(ClientCreator.createValidClient());
        Client clientByNickname = clientRepository.findByNickname(clientSaved.getNickname()).get();

        Assertions.assertThat(clientByNickname).isNotNull();
        Assertions.assertThat(clientByNickname.getIdClient()).isNotNull();
        Assertions.assertThat(clientByNickname.getNickname()).isEqualTo(clientSaved.getNickname());
    }

    @Test
    @DisplayName("Return client by CPF when successful")
    void returnClientByCPF_WhenSuccessful() {
        Client clientSaved = clientRepository.save(ClientCreator.createValidClient());
        Client clientByCPF = clientRepository.findByCpf(clientSaved.getCpf()).get();

        Assertions.assertThat(clientByCPF).isNotNull();
        Assertions.assertThat(clientByCPF.getCpf()).isEqualTo(clientSaved.getCpf());
    }

    @DirtiesContext
    @Test
    @DisplayName("Delete client by ID when successful")
    void deleteClientByID_WhenSuccessful() {
        Client clientSaved = clientRepository.save(ClientCreator.createValidClient());
        clientRepository.delete(clientSaved);
        Optional<Client> responseDelete = clientRepository.findById(clientSaved.getIdClient());

        Assertions.assertThat(responseDelete.isEmpty()).isTrue();
    }

    @DirtiesContext
    @Test
    @DisplayName("Update client when successful")
    void updateClient_WhenSuccessful() {
        Client clientSaved = clientRepository.save(ClientCreator.createValidClient());

        clientSaved.setName("Astrogildo");
        Client clientSavedao = clientRepository.save(clientSaved);

        Assertions.assertThat(clientSaved).isNotNull();
        Assertions.assertThat(clientSaved.getName()).isEqualTo("Astrogildo");
        Assertions.assertThat(clientSaved.getName()).isEqualTo(clientSavedao.getName());
    }

}
package com.salescontrol.service;

import com.salescontrol.creator.ClientCreator;
import com.salescontrol.dto.client.ClientPostDTO;
import com.salescontrol.model.Client;
import com.salescontrol.repository.ClientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepositoryMock;

    @BeforeEach
    void setUp() {
//        BDDMockito.when(clientRepositoryMock.save(ArgumentMatchers.any(Client.class)))
//                .thenReturn(ClientCreator.createValidClient());

        BDDMockito.when(clientRepositoryMock.findAll())
                .thenReturn(List.of(ClientCreator.createValidClient()));
    }

    @Test
    @DisplayName("When successful must save a client")
    void saveClient_WhenSuccessful() {
        ClientPostDTO clientPostDTOMock = ClientCreator.createClientPostDTO();
        ClientPostDTO clientPostDTO = clientService.saveClient(clientPostDTOMock);

        Assertions.assertThat(clientPostDTO.getName()).isNotNull()
                .isEqualTo(clientPostDTOMock.getName());
    }

    @Test
    @DisplayName("When successful must save all clients")
    void saveAllClients_WhenSuccessful() {
        List<ClientPostDTO> multipleClients = new ArrayList<>();
        ClientPostDTO clientPostDTOMock = ClientCreator.createClientPostDTO();
        ClientPostDTO clientPostDTOMock2 = ClientCreator.createClientPostDTO();
        multipleClients.add(clientPostDTOMock);
        multipleClients.add(clientPostDTOMock2);

        List<ClientPostDTO> clientPostDTOS = clientService.saveMultipleClients(multipleClients);

        Assertions.assertThat(clientPostDTOS).isNotNull().isNotEmpty();
        Assertions.assertThat(clientPostDTOS.get(0).getName()).isEqualTo(clientPostDTOMock.getName());
    }

    @Test
    @DisplayName("When successful must list all clients")
    void listAllClients_WhenSuccessful() {
        Client validClient = ClientCreator.createValidClient();
        List<Client> clients = clientService.listClients();

        Assertions.assertThat(clients).isNotNull().isNotEmpty();
        Assertions.assertThat(clients.get(0).getName()).isEqualTo(validClient.getName());
    }

}
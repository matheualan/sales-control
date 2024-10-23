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
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepositoryMock;

//    @BeforeEach
//    void setUp() {
//        BDDMockito.when(clientRepositoryMock.save(ArgumentMatchers.any(Client.class)))
//                .thenReturn(ClientCreator.createValidClient());
//    }

    @Test
    @DisplayName("When successful must save a client")
    void saveClient_WhenSuccessful() {
        ClientPostDTO clientPostDTOMock = ClientCreator.createClientPostDTO();
        ClientPostDTO clientPostDTO = clientService.saveClient(ClientCreator.createClientPostDTO());

        Assertions.assertThat(clientPostDTO.getName()).isNotNull()
                .isEqualTo(clientPostDTOMock.getName());
    }

    @Test
    @DisplayName("When successful must save all clients")
    void saveAllClients_WhenSuccessful() {
        ClientPostDTO clientPostDTOMock = ClientCreator.createClientPostDTO();
        ClientPostDTO clientPostDTO = clientService.saveClient(ClientCreator.createClientPostDTO());

        Assertions.assertThat(clientPostDTO.getName()).isNotNull()
                .isEqualTo(clientPostDTOMock.getName());
    }

}
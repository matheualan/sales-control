package com.salescontrol.controller;

import com.salescontrol.controller.client.ClientController;
import com.salescontrol.creator.ClientCreator;
import com.salescontrol.dto.client.ClientPostDTO;
import com.salescontrol.model.Client;
import com.salescontrol.service.ClientService;
import com.salescontrol.util.DateUtil;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientServiceMock;

    @Mock
    private DateUtil dateUtilMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(dateUtilMock.dateFormatter(ArgumentMatchers.any(LocalDateTime.class)))
                .thenReturn(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()));

        BDDMockito.when(clientServiceMock.saveClient(ArgumentMatchers.any(ClientPostDTO.class)))
                .thenReturn(ClientCreator.createClientPostDTO());

        BDDMockito.when(clientServiceMock.saveMultipleClients(ArgumentMatchers.any()))
                .thenReturn(List.of(ClientCreator.createClientPostDTO(), ClientCreator.createClientPostDTO()));
    }

    @Test
    @DisplayName("Must saved client when successful")
    void savedClient_WhenSuccessful() {
        ClientPostDTO clientPostDTO = ClientCreator.createClientPostDTO();
        ClientPostDTO responseOfSaveClient = clientController.saveClient(clientPostDTO).getBody();

        Assertions.assertThat(responseOfSaveClient).isNotNull();
        Assertions.assertThat(responseOfSaveClient.getName()).isEqualTo(clientPostDTO.getName()).isNotEmpty();
    }

    @Test
    @DisplayName("Must saved multiples clients when successful")
    void savedMultiplesClients_WhenSuccessful() {
        List<ClientPostDTO> listClients = new ArrayList<>();
        ClientPostDTO client = ClientCreator.createClientPostDTO();

        List<ClientPostDTO> responseSaveMultipleClients = clientController.saveMultipleClients(listClients).getBody();

        Assertions.assertThat(responseSaveMultipleClients).isNotNull().isNotEmpty();
        Assertions.assertThat(responseSaveMultipleClients.get(0).getName()).isEqualTo(client.getName());
    }

}
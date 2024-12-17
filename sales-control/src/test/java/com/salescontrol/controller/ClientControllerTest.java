package com.salescontrol.controller;

import com.salescontrol.controller.client.ClientController;
import com.salescontrol.creator.ClientCreator;
import com.salescontrol.dto.client.ClientPostDTO;
import com.salescontrol.dto.client.forAddress.ClientForAddressGetDTO;
import com.salescontrol.dto.client.forAddress.ClientForAddressPostDTO;
import com.salescontrol.service.ClientService;
import com.salescontrol.util.DateUtil;
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
                .thenReturn(List.of(ClientCreator.createClientPostDTO(), ClientCreator.createClientPostDTO2()));

        BDDMockito.when(clientServiceMock.saveClientWithAddress(ArgumentMatchers.any()))
                .thenReturn(ClientCreator.createClientWithAddress());

        BDDMockito.when(clientServiceMock.listClientsWithAddresses())
                .thenReturn(List.of(ClientCreator.getClientWithAddress()));
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

    @Test
    @DisplayName("Must saved client with address when successful")
    void savedClientWithAddress_WhenSuccessful() {
        ClientForAddressPostDTO clientWithAddress = ClientCreator.createClientWithAddress();

        ClientForAddressPostDTO responseSavedClientWithAddress = clientController.saveClientWithAddress(clientWithAddress).getBody();

        Assertions.assertThat(responseSavedClientWithAddress).isNotNull();
        Assertions.assertThat(responseSavedClientWithAddress.getName()).isEqualTo(clientWithAddress.getName()).isNotEmpty();
    }

    @Test
    @DisplayName("Returns a list of clients with addresses when successful")
    void returnsListOfClientsWithAddresses_WhenSuccessful() {
        List<ClientForAddressGetDTO> responseListClientsWithAddresses = clientController.listClientsWithAddresses().getBody();

        Assertions.assertThat(responseListClientsWithAddresses).isNotNull().isNotEmpty();
        Assertions.assertThat(responseListClientsWithAddresses.get(0).getName())
                .isEqualTo(ClientCreator.getClientWithAddress().getName());
    }

}
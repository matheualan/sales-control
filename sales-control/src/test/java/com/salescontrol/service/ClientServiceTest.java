//package com.salescontrol.service;
//
//import com.salescontrol.creator.ClientCreator;
//import com.salescontrol.dto.client.ClientGetDTO;
//import com.salescontrol.dto.client.ClientPostDTO;
//import com.salescontrol.model.Client;
//import com.salescontrol.model.Order;
//import com.salescontrol.repository.ClientRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.BDDMockito;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@ExtendWith(SpringExtension.class)
//class ClientServiceTest {
//
//    @InjectMocks
//    private ClientService clientService;
//
//    @Mock
//    private ClientRepository clientRepositoryMock;
//
//    @BeforeEach
//    void setUp() {
////        BDDMockito.when(clientRepositoryMock.save(ArgumentMatchers.any(Client.class)))
////                .thenReturn(ClientCreator.createValidClient());
//
//        BDDMockito.when(clientRepositoryMock.findAll())
//                .thenReturn(List.of(ClientCreator.createValidClient()));
//
//        BDDMockito.when(clientRepositoryMock.findByName(ArgumentMatchers.anyString()))
//                .thenReturn(Optional.of(ClientCreator.createValidClient()));
//
//        BDDMockito.when(clientRepositoryMock.findByNickname(ArgumentMatchers.anyString()))
//                .thenReturn(Optional.of(ClientCreator.createValidClient()));
//
//        BDDMockito.when(clientRepositoryMock.findByCpf(ArgumentMatchers.anyString()))
//                .thenReturn(Optional.of(ClientCreator.createValidClient()));
//
//        BDDMockito.when(clientRepositoryMock.findById(ArgumentMatchers.anyInt()))
//                .thenReturn(Optional.of(ClientCreator.createValidClient()));
//
////        BDDMockito.doNothing().when(clientRepositoryMock).delete(ArgumentMatchers.any(Client.class));
//    }
//
//    @Test
//    @DisplayName("When successful must save a client")
//    void saveClient_WhenSuccessful() {
//        ClientPostDTO clientPostDTOMock = ClientCreator.clientPostDTO();
//        ClientPostDTO clientPostDTO = clientService.saveClient(clientPostDTOMock);
//
//        Assertions.assertThat(clientPostDTO.getName()).isNotNull().isEqualTo(clientPostDTOMock.getName());
//    }
//
//    @Test
//    @DisplayName("When successful must save all clients")
//    void saveAllClients_WhenSuccessful() {
//        List<ClientPostDTO> multipleClients = new ArrayList<>();
//        ClientPostDTO clientPostDTOMock = ClientCreator.clientPostDTO();
//        ClientPostDTO clientPostDTOMock2 = ClientCreator.clientPostDTO();
//        multipleClients.add(clientPostDTOMock);
//        multipleClients.add(clientPostDTOMock2);
//
//        List<ClientPostDTO> clientPostDTOS = clientService.saveMultipleClients(multipleClients);
//
//        Assertions.assertThat(clientPostDTOS).isNotNull().isNotEmpty();
//        Assertions.assertThat(clientPostDTOS.get(0).getName()).isEqualTo(clientPostDTOMock.getName());
//    }
//
//    @Test
//    @DisplayName("When successful must list all clients")
//    void listAllClients_WhenSuccessful() {
//        Client validClient = ClientCreator.createValidClient();
//        List<Client> clients = clientService.listClients();
//        Client client = clientService.listClients().get(0);
//
//        Assertions.assertThat(clients).isNotNull().isNotEmpty();
//        Assertions.assertThat(clients.get(0).getName()).isEqualTo(validClient.getName());
//        Assertions.assertThat(clients).contains(client);
//    }
//
//    @Test
//    @DisplayName("When successful must find client by name")
//    void findClientByName_WhenSuccessful() {
//        Client validClient = ClientCreator.createValidClient();
//        ClientGetDTO responseClientByName = clientService.findByName(validClient.getName());
//
//        Assertions.assertThat(responseClientByName).isNotNull();
//        Assertions.assertThat(responseClientByName.getName()).isEqualTo(validClient.getName());
//    }
//
//    @Test
//    @DisplayName("When successful must find client by nickname")
//    void findClientByNickname_WhenSuccessful() {
//        Client validClient = ClientCreator.createValidClient();
//        ClientGetDTO responseClientByNickname = clientService.findByNickname(validClient.getNickname());
//
//        Assertions.assertThat(responseClientByNickname).isNotNull();
//        Assertions.assertThat(responseClientByNickname.getNickname()).isEqualTo(validClient.getNickname());
//    }
//
//    @Test
//    @DisplayName("When successful must find client by CPF")
//    void findClientByCPF_WhenSuccessful() {
//        Client validClient = ClientCreator.createValidClient();
//        ClientGetDTO responseClientByCPF = clientService.findByCpf(validClient.getCpf());
//
//        Assertions.assertThat(responseClientByCPF).isNotNull();
//        Assertions.assertThat(responseClientByCPF.getCpf()).isEqualTo(validClient.getCpf());
//    }
//
//    @Test
//    @DisplayName("Delete client by id when succesful")
//    void deleteClientById_WhenSuccesful() {
//        Assertions.assertThatCode(() -> clientService.deleteClient(1))
//                .doesNotThrowAnyException();
//    }
//
////    (testForMe)refatorar no futuro
//    @Test
//    @DisplayName("Updated client by id when successful")
//    void updatedClientById_WhenSuccessful() {
//        List<Order> orders = new ArrayList<>();
//
//        Client client = new Client(1,
//                "Matheus",
//                "Mths",
//                "12312312312",
//                orders,
//                null,
//                null,
//                null,
//                null);
//
//        Order order1 = new Order(1, client, 5.0, new BigDecimal("60.0"), LocalDateTime.now());
//        Order order2 = new Order(2, client, 10.0, new BigDecimal("130.0"), LocalDateTime.now());
//        orders.add(order1);
//        orders.add(order2);
//
//        client.setTotalOrders(2);
//        client.setTotalQuantity(15.0);
//        client.setTotalPurchased(new BigDecimal("190.0"));
//
//        Assertions.assertThat(client.getIdClient()).isEqualTo(1);
//        Assertions.assertThat(client.getOrders().get(0).getClient().getName()).isEqualTo(client.getName());
//
//        Assertions.assertThat(client.getOrders().get(0).getQuantity()).isEqualTo(5.0);
//
//        orders.get(0).setQuantity(4.0);
//        Assertions.assertThat(client.getOrders().get(0).getQuantity()).isEqualTo(4.0);
//        Assertions.assertThat(client.getName()).isEqualTo("Matheus");
//        Assertions.assertThat(client.getOrders().get(0).getClient().getName()).isEqualTo("Matheus");
//
//        client.setName("Alan");
//        Assertions.assertThat(client.getName()).isEqualTo("Alan");
//        Assertions.assertThat(client.getTotalOrders()).isEqualTo(2);
//        Assertions.assertThat(client.getTotalQuantity()).isEqualTo(15.0);
//        Assertions.assertThat(client.getTotalPurchased()).isEqualTo("190.0");
//    }
//
//}
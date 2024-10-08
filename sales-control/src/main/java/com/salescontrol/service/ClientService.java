package com.salescontrol.service;

import com.salescontrol.dto.client.ClientGetDTO;
import com.salescontrol.dto.client.ClientPostDTO;
import com.salescontrol.dto.client.ClientWithOrderGetDTO;
import com.salescontrol.dto.client.ClientWithOrderPostDTO;
import com.salescontrol.dto.client.forAddress.ClientForAddressGetDTO;
import com.salescontrol.dto.client.forAddress.ClientForAddressPostDTO;
import com.salescontrol.exception.ClientNotFoundException;
import com.salescontrol.mapper.ClientMapper;
import com.salescontrol.model.Client;
import com.salescontrol.model.Order;
import com.salescontrol.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientPostDTO saveClient(ClientPostDTO clientPostDTO) {
        Client client = ClientMapper.INSTANCE.toClient(clientPostDTO);
        clientRepository.save(client);
        return clientPostDTO;
    }

    public List<ClientPostDTO> saveMultipleClients(List<ClientPostDTO> multipleClients) {
        List<Client> clients = new ArrayList<>();
        for (ClientPostDTO clientPost : multipleClients) {
            clients.add(ClientMapper.INSTANCE.toClient(clientPost));
        }
        clientRepository.saveAll(clients);
        return multipleClients;
    }

    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    public List<ClientGetDTO> listClientsDTO() {
        List<Client> clients = clientRepository.findAll();
        List<ClientGetDTO> clientsDTO = new ArrayList<>();
        for (Client client : clients) {
            clientsDTO.add(ClientMapper.INSTANCE.toClientGet(client));
        }
        return clientsDTO;
    }

    public ClientGetDTO findByName(String name) {
        Client client = clientRepository.findByName(name).orElseThrow(
                () -> new ClientNotFoundException("Cliente com o nome '" + name + "' não encontrado."));
        return ClientMapper.INSTANCE.toClientGet(client);
    }

    public ClientGetDTO findByNickname(String nickname) {
        Client client = clientRepository.findByNickname(nickname).orElseThrow(
                () -> new ClientNotFoundException("Cliente com o apelido '" + nickname + "' não encontrado"));
        return ClientMapper.INSTANCE.toClientGet(client);
    }

    public ClientGetDTO findByCpf(String cpf) {
        Client client = clientRepository.findByCpf(cpf).orElseThrow(
                () -> new ClientNotFoundException("Cliente com o CPF '" + cpf + "' não encontrado."));
        return ClientMapper.INSTANCE.toClientGet(client);
    }

    public void deleteClient(Integer id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ClientNotFoundException("Cliente não encontrado."));
        clientRepository.delete(client);
    }

    public void updatedClient(Integer id, ClientPostDTO clientPostDTO) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ClientNotFoundException("Cliente não encontrado."));

        BeanUtils.copyProperties(clientPostDTO, client);
        clientRepository.save(client);
    }

//    LÓGICA DE CLIENT COM ORDER
    public ClientWithOrderPostDTO saveClientWithOrder(ClientWithOrderPostDTO clientWithOrderPostDTO) {
        Client client = ClientMapper.INSTANCE.toClient(clientWithOrderPostDTO);

        for (Order order : client.getOrders()) {
            order.setClient(client);
        }

        clientRepository.save(client);
        return clientWithOrderPostDTO;
    }

    public List<ClientWithOrderGetDTO> listClientWithOrder() {
        List<Client> clients = clientRepository.findAll();
        List<ClientWithOrderGetDTO> listDTO = new ArrayList<>();
        for (Client client : clients) {
            ClientWithOrderGetDTO clientWithOrderGetDTO = ClientMapper.INSTANCE.toClientWithOrderGet(client);
            listDTO.add(clientWithOrderGetDTO);
        }
        return listDTO;
    }

//    LÓGICA DE CLIENT COM ADDRESS
    public ClientForAddressPostDTO saveClientWithAddress(ClientForAddressPostDTO clientForAddressPostDTO) {
        Client client = ClientMapper.INSTANCE.toClient(clientForAddressPostDTO);
        clientRepository.save(client);
        return clientForAddressPostDTO;
    }

    public List<ClientForAddressGetDTO> listClientsWithAddresses() {
        List<Client> allClients = clientRepository.findAll();
        List<ClientForAddressGetDTO> listClientsWithAddresses = new ArrayList<>();
        for (Client client : allClients) {
            listClientsWithAddresses.add(ClientMapper.INSTANCE.toClientWithAddress(client));
        }
        return listClientsWithAddresses;
    }
}

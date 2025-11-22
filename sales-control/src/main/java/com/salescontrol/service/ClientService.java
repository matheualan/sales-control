package com.salescontrol.service;

import com.salescontrol.dto.address.AddressPostDTO;
import com.salescontrol.dto.client.ClientGetDTO;
import com.salescontrol.dto.client.ClientPostDTO;
import com.salescontrol.dto.client.ClientPutDTO;
import com.salescontrol.dto.client.ClientWithOrderGetDTO;
import com.salescontrol.dto.client.forAddress.ClientForAddressGetDTO;
import com.salescontrol.dto.client.forAddress.ClientForAddressPostDTO;
import com.salescontrol.dto.client.forAddress.ClientWithAddressViaCep;
import com.salescontrol.exception.ClientNotFoundException;
import com.salescontrol.mapper.AddressMapper;
import com.salescontrol.mapper.ClientMapper;
import com.salescontrol.mapper.ClientMapperInterface;
import com.salescontrol.model.Address;
import com.salescontrol.model.Client;
import com.salescontrol.repository.AddressRepository;
import com.salescontrol.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapperInterface clientMapperInterface;
    private final ViaCepService viaCepService;

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

    public Page<ClientGetDTO> clientsPageDTO(Pageable pageable) {
        return clientRepository.findAll(pageable).map(ClientMapper.INSTANCE::toClientGet);
    }

    public ClientGetDTO findByName(String name) {
        Client client = clientRepository.findByName(name).orElseThrow(
                () -> new ClientNotFoundException("Cliente com o nome '" + name + "' não encontrado."));
        return ClientMapper.INSTANCE.toClientGet(client);
    }

    public List<ClientGetDTO> findByNickname(String nickname) {
        List<Client> clients = clientRepository.findByNickname(nickname);
        List<ClientGetDTO> clientsDto = new ArrayList<>();
        for (Client client : clients) {
            clientsDto.add(ClientMapper.INSTANCE.toClientGet(client));
        }
        return clientsDto;
    }

    public ClientGetDTO findByCpf(String cpf) {
        Client client = clientRepository.findByCpf(cpf).orElseThrow(
                () -> new ClientNotFoundException("Cliente com o CPF '" + cpf + "' não encontrado."));
        return ClientMapper.INSTANCE.toClientGet(client);
    }

    public List<ClientGetDTO> findAllByName(String name) {
        List<Client> listByName = clientRepository.findByNameContaining(name);
        List<ClientGetDTO> listByNameDTO = new ArrayList<>();

        for (Client client : listByName) {
            listByNameDTO.add(ClientMapper.INSTANCE.toClientGet(client));
        }

        return listByNameDTO;
    }

    public Client findById(Integer id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new ClientNotFoundException("Cliente com o ID " + id + " não encontrado."));
    }

    public void deleteClient(Integer id) {
        clientRepository.delete(findById(id));
    }

    public void updatedClient(Integer id, ClientPostDTO clientPostDTO) {
        Client client = findById(id);
        BeanUtils.copyProperties(clientPostDTO, client);
        clientRepository.save(client);
    }

    public ClientGetDTO updateClient(Integer id, ClientPutDTO clientPutDTO) {
        Client client = findById(id);
        clientMapperInterface.updateClientFromDto(clientPutDTO, client);
        clientRepository.save(client);
        return ClientMapper.INSTANCE.toClientGet(client);
    }

//    Opção para metodo update usando reflection
//    public Optional<ClientGetDTO> updateClient(Integer id, ClientPostDTO clientPostDTO) {
//        var clientById = findById(id);
//        copyNonNullProperties(clientPostDTO, clientById);
//        clientRepository.save(clientById);
//        return Optional.of(ClientMapper.INSTANCE.toClientGet(clientById));
//    }
//
//    public void copyNonNullProperties(ClientPostDTO clientSource, Client clientTarget) {
//        BeanWrapperImpl srcWrapper = new BeanWrapperImpl(clientSource);
//        BeanWrapperImpl trgWrapper = new BeanWrapperImpl(clientTarget);
//
//        for (PropertyDescriptor propertyDescriptor : srcWrapper.getPropertyDescriptors()) {
//            String propertyName = propertyDescriptor.getName();
//
//            if (srcWrapper.getPropertyValue(propertyName) != null &&
//            trgWrapper.isWritableProperty(propertyName)) {
//                Object propertyValue = srcWrapper.getPropertyValue(propertyName);
//                trgWrapper.setPropertyValue(propertyName, propertyValue);
//            }
//        }
//    }

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

    private final AddressRepository addressRepository;
    public ClientWithAddressViaCep saveClientWithAddressViaCep(ClientWithAddressViaCep clientWithAddressViaCep) {
        AddressPostDTO addressPostDTO = viaCepService.buscarCep(clientWithAddressViaCep.getCep());
        Address address = AddressMapper.INSTANCE.toAddress(addressPostDTO);
        address.setComplemento(clientWithAddressViaCep.getComplemento());
        Address savedAddress = addressRepository.save(address);

        Client client = ClientMapper.INSTANCE.toClient(clientWithAddressViaCep);
        client.setTotalOrders(0);
        client.setTotalQuantity(0.0);
        client.setTotalPurchased(BigDecimal.ZERO);

        if (client.getAddresses() == null) {
            client.setAddresses(new ArrayList<>());
        }
        client.getAddresses().add(savedAddress);
        Client savedClient = clientRepository.save(client);

        return ClientMapper.INSTANCE.toClientWithAddressViaCep(savedClient);
    }


}

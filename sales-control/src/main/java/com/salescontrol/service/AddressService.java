package com.salescontrol.service;

import com.salescontrol.dto.address.AddressDTO;
import com.salescontrol.dto.address.AddressPostDTO;
import com.salescontrol.exception.ClientNotFoundException;
import com.salescontrol.mapper.AddressMapper;
import com.salescontrol.model.Address;
import com.salescontrol.model.Client;
import com.salescontrol.repository.AddressRepository;
import com.salescontrol.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;

    public AddressPostDTO saveAddress(AddressPostDTO addressPostDTO, String name) {
        Address address = AddressMapper.INSTANCE.toAddress(addressPostDTO);
        addressRepository.save(address);
        Client client = clientRepository.findByName(name)
                .orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado."));
        client.getAddresses().add(address);
        clientRepository.save(client);
        return addressPostDTO;
    }

    public List<AddressDTO> listAllAddresses() {
        List<AddressDTO> listAddressesDTO = new ArrayList<>();
        List<Address> allAddresses = addressRepository.findAll();
        for (Address a : allAddresses) {
            AddressDTO addressDTO = AddressMapper.INSTANCE.toAddressDTO(a);
            listAddressesDTO.add(addressDTO);
        }
        return listAddressesDTO;
    }
}

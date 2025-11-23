package com.salescontrol.service;

import com.salescontrol.dto.address.AddressPostDTO;
import com.salescontrol.mapper.AddressMapper;
import com.salescontrol.model.Address;
import com.salescontrol.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final AddressRepository addressRepository;

    public AddressPostDTO findAndSaveAddressByCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String urlViaCep = "https://viacep.com.br/ws/" + cep + "/json/";
        ResponseEntity<AddressPostDTO> response = restTemplate.getForEntity(urlViaCep, AddressPostDTO.class);
        AddressPostDTO addressPostDTO = response.getBody();
        Address address = AddressMapper.INSTANCE.toAddress(addressPostDTO);
        addressRepository.save(address);
        return addressPostDTO;
    }

    public AddressPostDTO findAddressByCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String urlViaCep = "https://viacep.com.br/ws/" + cep + "/json/";
        ResponseEntity<AddressPostDTO> response = restTemplate.getForEntity(urlViaCep, AddressPostDTO.class);
        return response.getBody();
    }

}
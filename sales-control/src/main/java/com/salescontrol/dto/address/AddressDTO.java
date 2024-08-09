package com.salescontrol.dto.address;

import lombok.Data;

@Data
public class AddressDTO {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;

}

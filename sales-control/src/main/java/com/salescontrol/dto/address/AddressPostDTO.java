package com.salescontrol.dto.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressPostDTO {

    @NotBlank(message = "O campo CEP deve conter 8 números")
    @Size(min = 8, max = 8, message = "O campo CEP deve conter 8 números")
    private String cep;

//    @JsonProperty("rua")
    private String logradouro;

//    @JsonProperty("referencia")
    private String complemento;

    @JsonProperty
    private String bairro;

//    @JsonProperty(value = "estado")
    private String localidade;

}

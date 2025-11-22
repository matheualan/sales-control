package com.salescontrol.dto.client.forAddress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties({"totalOrders", "totalQuantity", "totalPurchased"})
public class ClientWithAddressViaCep {

    private String name;
    private String nickname;
    private String cpf;
    private String cep;
    private String complemento;

//    private Integer totalOrders = 0;
//    private Double totalQuantity = 0.0;
//    private BigDecimal totalPurchased = BigDecimal.ZERO;

}
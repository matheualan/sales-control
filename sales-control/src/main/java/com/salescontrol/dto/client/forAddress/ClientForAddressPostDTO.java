package com.salescontrol.dto.client.forAddress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.salescontrol.dto.address.AddressPostDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"totalOrders", "totalQuantity", "totalPurchased"})
public class ClientForAddressPostDTO {

    private String name;
    private String nickname;
    private String cpf;

    private Integer totalOrders = 0;
    private Double totalQuantity = 0.0;
    private BigDecimal totalPurchased = BigDecimal.ZERO;

    private List<AddressPostDTO> addresses;

}

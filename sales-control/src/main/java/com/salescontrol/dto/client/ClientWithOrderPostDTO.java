package com.salescontrol.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.salescontrol.dto.order.OrderPostDTO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
//@NoArgsConstructor
//@AllArgsConstructor

public class ClientWithOrderPostDTO {

    private ClientPostDTO clientPostDTO;
    private List<OrderPostDTO> ordersPostDTO;

}

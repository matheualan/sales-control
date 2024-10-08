package com.salescontrol.dto.order;

import com.salescontrol.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderGetDTO {

    private Double quantity;
    private BigDecimal price;
    private Client client;

}

package com.salescontrol.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPostDTO {

    private String nameClient;
    private Double quantity;
    private BigDecimal price;

}

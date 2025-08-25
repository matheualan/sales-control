package com.salescontrol.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salescontrol.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderGetDTO {

    private Integer idOrder;

    private Double quantity;

    private BigDecimal price;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    private Client client;
}

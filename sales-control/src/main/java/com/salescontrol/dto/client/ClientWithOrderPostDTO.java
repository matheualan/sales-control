package com.salescontrol.dto.client;

import com.salescontrol.dto.order.OrderPostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

//@JsonIgnoreProperties(value = {"client"})
public class ClientWithOrderPostDTO {

    private String name;
    private String cpf;
    private List<OrderPostDTO> orders;

}

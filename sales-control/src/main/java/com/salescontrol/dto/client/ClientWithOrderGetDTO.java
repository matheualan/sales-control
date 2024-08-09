package com.salescontrol.dto.client;

import com.salescontrol.dto.order.OrderForClientGetDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientWithOrderGetDTO {

    private String name;
    private String nickname;
    private String cpf;
    private List<OrderForClientGetDTO> orders;

}

package com.salescontrol.dto.client;

import com.salescontrol.dto.order.OrderForClientGetDTO;
import lombok.Data;

import java.util.List;

@Data
public class ClientWithOrderGetDTO {

    private String name;
    private String nickname;
    private String cpf;
    private List<OrderForClientGetDTO> orders;

}

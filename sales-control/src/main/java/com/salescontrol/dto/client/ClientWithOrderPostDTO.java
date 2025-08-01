package com.salescontrol.dto.client;

import com.salescontrol.dto.order.OrderPostDTO;
import lombok.Data;

import java.util.List;

@Data

public class ClientWithOrderPostDTO {

    private ClientPostDTO clientPostDTO;
    private List<OrderPostDTO> ordersPostDTO;

}

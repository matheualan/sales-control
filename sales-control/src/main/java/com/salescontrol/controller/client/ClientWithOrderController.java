package com.salescontrol.controller.client;

import com.salescontrol.dto.client.ClientWithOrderGetDTO;
import com.salescontrol.dto.client.ClientWithOrderPostDTO;
import com.salescontrol.service.ClientService;
import com.salescontrol.service.OrderService;
import com.salescontrol.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/client-with-order")
@RequiredArgsConstructor
@Log4j2
public class ClientWithOrderController {

    private final ClientService clientService;
    private final OrderService orderService;
    private final DateUtil dateUtil;

    @PostMapping(value = "/save-client-with-order")
    public ResponseEntity<ClientWithOrderPostDTO> saveClientWithOrder(@RequestBody ClientWithOrderPostDTO client) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" POST saveClientWithOrder()"));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveClientWithOrder(client));
    }

    @GetMapping(value = "/list-clients-with-orders")
    public ResponseEntity<List<ClientWithOrderGetDTO>> listClientWithOrder() {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET listClientWithOrder()"));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.listClientWithOrder());
    }

}

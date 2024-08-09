package com.salescontrol.controller.order;

import com.salescontrol.dto.order.OrderGetDTO;
import com.salescontrol.dto.order.OrderPostDTO;
import com.salescontrol.model.Order;
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
@RequestMapping(value = "/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService orderService;
    private final DateUtil dateUtil;

    @PostMapping(value = "/create-order-byName")
    public ResponseEntity<OrderGetDTO> createOrderByName(@RequestBody OrderPostDTO orderPostDTO) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" POST createOrder()"));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderPostDTO));
    }

    @GetMapping(value = "/list-all-orders")
    public ResponseEntity<List<Order>> listAllOrders() {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET listAllOrders()"));
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAllOrders());
    }

}

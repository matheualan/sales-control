package com.salescontrol.controller.order;

import com.salescontrol.dto.client.ClientWithOrderPostDTO;
import com.salescontrol.dto.order.OrderGetDTO;
import com.salescontrol.dto.order.OrderPostDTO;
import com.salescontrol.model.Order;
import com.salescontrol.service.OrderService;
import com.salescontrol.service.ReportService;
import com.salescontrol.util.DateUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
@Log4j2
@SecurityRequirement(name = "bearerAuth")
public class OrderController {

    private final OrderService orderService;
    private final DateUtil dateUtil;
    private final ReportService reportService;

    @PostMapping(value = "/create-byName")
    public ResponseEntity<OrderGetDTO> createOrderByName(@RequestBody OrderPostDTO orderPostDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderPostDTO));
    }

    @PostMapping(value = "/create-client-with-order")
    public ResponseEntity<ClientWithOrderPostDTO> saveClientWithOrder(@RequestBody ClientWithOrderPostDTO client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveClientWithOrder(client));
    }

    @GetMapping(value = "/list-all")
    public ResponseEntity<List<Order>> listAllOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAllOrders());
    }

    @GetMapping(value = "/pagination")
    public ResponseEntity<Page<OrderGetDTO>> orderPage(@PageableDefault(page = 0, size = 5,
    direction = Sort.Direction.DESC, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.pageOrders(pageable));
    }

    @DeleteMapping(value = "/delete-byId/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Integer id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/reports-sales")
    public ResponseEntity<Map<String, Object>> getTotals() {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getTotals());
    }

}
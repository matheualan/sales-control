package com.salescontrol.controller.order;

import com.salescontrol.dto.client.ClientWithOrderPostDTO;
import com.salescontrol.dto.order.OrderGetDTO;
import com.salescontrol.dto.order.OrderPostDTO;
import com.salescontrol.model.Order;
import com.salescontrol.service.OrderService;
import com.salescontrol.service.ReportService;
import com.salescontrol.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService orderService;
    private final DateUtil dateUtil;
    private final ReportService reportService;

    @PostMapping(value = "/create-byName")
    public ResponseEntity<OrderGetDTO> createOrderByName(@RequestBody OrderPostDTO orderPostDTO) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" POST createOrder()"));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderPostDTO));
    }

    @PostMapping(value = "/create-client-with-order")
    public ResponseEntity<ClientWithOrderPostDTO> saveClientWithOrder(@RequestBody ClientWithOrderPostDTO client) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" POST saveClientWithOrder()"));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveClientWithOrder(client));
    }

    @GetMapping(value = "/list-all")
    public ResponseEntity<List<Order>> listAllOrders() {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET listAllOrders()"));
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAllOrders());
    }

    @GetMapping(value = "/pagination")
    public ResponseEntity<Page<OrderGetDTO>> orderPage(@PageableDefault(page = 0, size = 5,
    direction = Sort.Direction.DESC, sort = "client") Pageable pageable) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET orderPage()"));
        return ResponseEntity.status(HttpStatus.OK).body(orderService.pageOrders(pageable));
    }

    @DeleteMapping(value = "/delete-byId/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Integer id) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" DELETE deleteOrderById()"));
        orderService.deleteOrderById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/reports-sales")
    public ResponseEntity<Map<String, Object>> getTotals() {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" GET getTotals()"));
        Map<String, Object> result = new HashMap<>();
        result.put("Total de pedidos: ", reportService.sumTotalOrders());
        result.put("Total de quantidades: ", reportService.sumQuantites());
        result.put("Total de valores: ", reportService.sumPrices());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}

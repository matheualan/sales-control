package com.salescontrol.service;

import com.salescontrol.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final OrderRepository orderRepository;

    public Integer sumTotalOrders() {
        return orderRepository.sumTotalOrders();
    }

    public Double sumQuantites() {
        return orderRepository.sumQuantities();
    }

    public BigDecimal sumPrices() {
        return orderRepository.sumPrices();
    }

}

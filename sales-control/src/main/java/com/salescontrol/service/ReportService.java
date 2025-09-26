package com.salescontrol.service;

import com.salescontrol.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    public Map<String, Object> getTotals() {
        Map<String, Object> result = new HashMap<>();
        result.put("Total de pedidos: ", orderRepository.sumTotalOrders());
        result.put("Quantidade total: ", orderRepository.sumQuantities());
        result.put("Valor total: ", orderRepository.sumPrices());
        return result;
    }

}
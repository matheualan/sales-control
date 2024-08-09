package com.salescontrol.service;

import com.salescontrol.model.SalesData;
import com.salescontrol.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesDataService {

    @Autowired
    private OrderRepository orderRepository;

    public void populateMostRequested(SalesData salesData) {
        List<Object[]> results = orderRepository.findMostRequestedQuantities();

        if (!results.isEmpty()) {
            // Preenche mostRequested com a quantidade mais solicitada
            salesData.setMostRequested((Double) results.get(0)[0]);

            // Preenche listMostRequested com todas as quantidades ordenadas por frequência
            List<Double> mostRequestedList = results.stream()
                    .map(result -> (Double) result[0])
                    .collect(Collectors.toList());
            salesData.setListMostRequested(mostRequestedList);
        }
    }
}

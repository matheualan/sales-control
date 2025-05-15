package com.salescontrol.service;

import com.salescontrol.dto.order.OrderGetDTO;
import com.salescontrol.dto.order.OrderPostDTO;
import com.salescontrol.exception.ClientNotFoundException;
import com.salescontrol.mapper.OrderMapper;
import com.salescontrol.model.Client;
import com.salescontrol.model.Order;
import com.salescontrol.repository.ClientRepository;
import com.salescontrol.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public OrderGetDTO createOrder(OrderPostDTO orderPostDTO) {
        Client client = clientRepository.findByName(orderPostDTO.getNameClient())
                .orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado"));

        Order order = OrderMapper.INSTANCE.toOrder(orderPostDTO);

        client.setTotalOrders(client.getTotalOrders() + 1);
        client.setTotalPurchased(client.getTotalPurchased().add(orderPostDTO.getPrice()));
        client.setTotalQuantity(client.getTotalQuantity() + orderPostDTO.getQuantity());

        order.setClient(client);

        orderRepository.save(order);

        return OrderMapper.INSTANCE.toOrderGet(order);
    }

    public void deleteOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        orderRepository.delete(order);
    }

}

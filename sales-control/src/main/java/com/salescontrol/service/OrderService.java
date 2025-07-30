package com.salescontrol.service;

import com.salescontrol.dto.client.ClientPostDTO;
import com.salescontrol.dto.client.ClientWithOrderGetDTO;
import com.salescontrol.dto.client.ClientWithOrderPostDTO;
import com.salescontrol.dto.order.OrderGetDTO;
import com.salescontrol.dto.order.OrderPostDTO;
import com.salescontrol.exception.ClientNotFoundException;
import com.salescontrol.mapper.ClientMapper;
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

    public ClientWithOrderPostDTO saveClientWithOrder(ClientWithOrderPostDTO clientWithOrderPostDTO) {
        ClientPostDTO clientPostDTO = clientWithOrderPostDTO.getClientPostDTO();
        List<OrderPostDTO> ordersPostDTO = clientWithOrderPostDTO.getOrdersPostDTO();

        Client client = ClientMapper.INSTANCE.toClient(clientPostDTO);
        clientRepository.save(client);

        for (OrderPostDTO order : ordersPostDTO) {
            int count = 0;
            createOrder(clientWithOrderPostDTO.getOrdersPostDTO().get(count));
            count++;
        }

        return clientWithOrderPostDTO;
    }

    public void deleteOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        orderRepository.delete(order);
    }

}

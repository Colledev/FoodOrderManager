package com.example.foodordermanager.order;

import com.example.foodordermanager.order.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::mapToOrderDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        OrderEntity order = OrderMapper.mapToOrder(orderDTO);
        OrderEntity savedOrder = orderRepository.save(order);
        return OrderMapper.mapToOrderDTO(savedOrder);
    }

    public OrderEntity findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }



}

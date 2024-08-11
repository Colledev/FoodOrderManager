package com.example.foodordermanager.order;

import com.example.foodordermanager.order.dto.OrderDTO;

public class OrderMapper {
    public static OrderDTO mapToOrderDTO(OrderEntity order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setPriceTotal(order.getPriceTotal());
        return orderDTO;
    }

    public static OrderEntity mapToOrder(OrderDTO orderDTO) {
        OrderEntity order = new OrderEntity();
        order.setId(orderDTO.getId());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setPriceTotal(orderDTO.getPriceTotal());
        return order;
    }
}
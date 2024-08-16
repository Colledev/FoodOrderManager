package com.example.foodordermanager.order.dto;

import com.example.foodordermanager.order.OrderStatus;

public class OrderStatusUpdateDTO {
    private OrderStatus orderStatus;

    // Getters and setters
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
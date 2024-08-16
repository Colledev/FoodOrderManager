package com.example.foodordermanager.order.dto;

import com.example.foodordermanager.order.OrderStatus;
import com.example.foodordermanager.orderproduct.dto.OrderProductDTO;

import java.util.List;

public class OrderDetailsDTO {
    private Long orderId;
    private OrderStatus orderStatus;
    private Double priceTotal;
    private List<OrderProductDTO> products;

    // Getters and Setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public List<OrderProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDTO> products) {
        this.products = products;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}

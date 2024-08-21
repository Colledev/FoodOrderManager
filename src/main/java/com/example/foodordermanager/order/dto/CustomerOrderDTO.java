package com.example.foodordermanager.order.dto;

import com.example.foodordermanager.orderproduct.dto.OrderProductDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerOrderDTO {

    private String customerName;
    private BigDecimal priceTotal;
    private List<OrderProductDTO> products;

    // Getters and Setters

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }

    public List<OrderProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDTO> products) {
        this.products = products;
    }
}
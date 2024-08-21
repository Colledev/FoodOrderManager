package com.example.foodordermanager.order.dto;

import com.example.foodordermanager.order.OrderEntity;
import com.example.foodordermanager.orderproduct.dto.OrderProductDTO;

import java.math.BigDecimal;
import java.util.List;

public class OrderTableDTO {

    private Integer tableNumber;
    private BigDecimal totalPrice;
    private List<CustomerOrderDTO> customerOrders;


    // Getters e Setters
    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CustomerOrderDTO> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrderDTO> customerOrders) {
        this.customerOrders = customerOrders;
    }
}

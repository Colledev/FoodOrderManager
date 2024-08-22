package com.example.foodordermanager.payment.dto;

import java.math.BigDecimal;

public class PaymentDTO {

    private Long id;
    private String method;
    private BigDecimal amount;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

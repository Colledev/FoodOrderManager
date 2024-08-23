package com.example.foodordermanager.payment.dto;

import java.math.BigDecimal;

public class PaymentDTO {

    private String method;
    private BigDecimal amount;

    // Getters and Setters

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
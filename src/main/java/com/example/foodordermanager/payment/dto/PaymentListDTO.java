package com.example.foodordermanager.payment.dto;

import java.util.List;

public class PaymentListDTO {
    private List<PaymentDTO> payments;

    // Getters e Setters

    public List<PaymentDTO> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentDTO> payments) {
        this.payments = payments;
    }
}

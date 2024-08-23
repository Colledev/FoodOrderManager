package com.example.foodordermanager.payment;

import com.example.foodordermanager.payment.dto.PaymentDTO;

public class PaymentMapper {

    public static PaymentDTO mapToPaymentDTO(PaymentEntity payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setMethod(payment.getMethod().name());
        dto.setAmount(payment.getAmount());
        return dto;
    }

    public static PaymentEntity mapToPayment(PaymentDTO payment) {
        PaymentEntity entity = new PaymentEntity();
        entity.setMethod(PaymentMethod.valueOf(payment.getMethod()));
        entity.setAmount(payment.getAmount());
        return entity;
    }
}
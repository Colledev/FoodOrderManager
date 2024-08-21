package com.example.foodordermanager.payment;

import com.example.foodordermanager.payment.dto.PaymentDTO;

public class PaymentMapper {

    public static PaymentDTO mapToPaymentDTO(PaymentEntity payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setMethod(payment.getMethod());
        dto.setAmount(payment.getAmount());
        return dto;
    }

    public static PaymentEntity mapToPayment(PaymentDTO payment) {
        PaymentEntity entity = new PaymentEntity();
        entity.setId(payment.getId());
        entity.setMethod(payment.getMethod());
        entity.setAmount(payment.getAmount());
        return entity;
    }
}

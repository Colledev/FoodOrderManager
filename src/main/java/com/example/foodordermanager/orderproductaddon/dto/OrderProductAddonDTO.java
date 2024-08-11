package com.example.foodordermanager.orderproductaddon.dto;

public class OrderProductAddonDTO {
    private Long addonId;
    private Integer quantity;

    // Getters e Setters

    public Long getAddonId() {
        return addonId;
    }

    public void setAddonId(Long addonId) {
        this.addonId = addonId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

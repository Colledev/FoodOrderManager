package com.example.foodordermanager.orderproduct.dto;

import com.example.foodordermanager.orderproductaddon.dto.OrderProductAddonDTO;

import java.math.BigDecimal;
import java.util.List;

public class OrderProductDTO {

    private Long orderId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private List<OrderProductAddonDTO> addons;

    // Getters and Setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<OrderProductAddonDTO> getAddons() {
        return addons;
    }

    public void setAddons(List<OrderProductAddonDTO> addons) {
        this.addons = addons;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

package com.example.foodordermanager.orderproduct.dto;

import com.example.foodordermanager.orderproductaddon.dto.OrderProductAddonDTO;
import java.util.List;

public class OrderProductDTO {

    private Long Id;
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private String productName;
    private List<OrderProductAddonDTO> addons;

    // Getters and Setters

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<OrderProductAddonDTO> getAddons() {
        return addons;
    }

    public void setAddons(List<OrderProductAddonDTO> addons) {
        this.addons = addons;
    }
}

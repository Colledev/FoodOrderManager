package com.example.foodordermanager.orderproductaddon;

import com.example.foodordermanager.addon.AddonEntity;
import com.example.foodordermanager.orderproduct.OrderProductEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "order_product_addon")
public class OrderProductAddonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_product_id", nullable = false)
    private OrderProductEntity orderProduct;

    @ManyToOne
    @JoinColumn(name = "addon_id", nullable = false)
    private AddonEntity addon;

    private Integer quantity;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderProductEntity getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProductEntity orderProduct) {
        this.orderProduct = orderProduct;
    }

    public AddonEntity getAddon() {
        return addon;
    }

    public void setAddon(AddonEntity addon) {
        this.addon = addon;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

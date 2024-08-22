package com.example.foodordermanager.productaddon;

import jakarta.persistence.*;

@Entity
@IdClass(ProductAddonId.class)
@Table(name = "product_addon")
public class ProductAddonEntity {

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "addon_id")
    private Long addonId;

    // Getters and setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getAddonId() {
        return addonId;
    }

    public void setAddonId(Long addonId) {
        this.addonId = addonId;
    }
}

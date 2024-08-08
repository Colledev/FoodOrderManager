package com.example.foodordermanager.productaddon;

import java.io.Serializable;
import java.util.Objects;

public class ProductAddonId implements Serializable {
    private Long productId;
    private Long addonId;

    // Default constructor
    public ProductAddonId() {}

    public ProductAddonId(Long productId, Long addonId) {
        this.productId = productId;
        this.addonId = addonId;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(productId, addonId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductAddonId that = (ProductAddonId) obj;
        return Objects.equals(productId, that.productId) && Objects.equals(addonId, that.addonId);
    }
}

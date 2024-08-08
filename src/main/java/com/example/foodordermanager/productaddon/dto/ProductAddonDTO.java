package com.example.foodordermanager.productaddon.dto;

import com.example.foodordermanager.addon.dto.AddonDTO;
import java.util.List;

public class ProductAddonDTO {

    private Long productId;
    private String productName;
    private List<AddonDTO> addons;

    public ProductAddonDTO() {
    }

    public ProductAddonDTO(Long productId, String productName, List<AddonDTO> addons) {
        this.productId = productId;
        this.productName = productName;
        this.addons = addons;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<AddonDTO> getAddons() {
        return addons;
    }

    public void setAddons(List<AddonDTO> addons) {
        this.addons = addons;
    }
}

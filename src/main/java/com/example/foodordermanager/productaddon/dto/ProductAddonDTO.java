package com.example.foodordermanager.productaddon.dto;

import com.example.foodordermanager.addon.dto.AddonDTO;

import java.math.BigDecimal;
import java.util.List;

public class ProductAddonDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private List<AddonDTO> addons;

    public ProductAddonDTO(Long id, String name, BigDecimal price, List<AddonDTO> addons) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.addons = addons;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<AddonDTO> getAddons() {
        return addons;
    }

    public void setAddons(List<AddonDTO> addons) {
        this.addons = addons;
    }
}

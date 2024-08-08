package com.example.foodordermanager.addon.dto;

public class AddonDTO {

    private Long id;
    private String name;
    private Double price;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPrice(Double price) {
        this.price = price;
    }
}

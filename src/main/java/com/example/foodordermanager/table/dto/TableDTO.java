package com.example.foodordermanager.table.dto;

public class TableDTO {
    private Long id;
    private Integer number;
    private Integer seats;
    private Boolean available;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}

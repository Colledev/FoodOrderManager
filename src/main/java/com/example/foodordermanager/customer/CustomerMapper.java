package com.example.foodordermanager.customer;

import com.example.foodordermanager.customer.dto.CustomerDTO;

public class CustomerMapper {

    public static CustomerEntity mapToEntity(CustomerDTO customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customer.getId());
        entity.setName(customer.getName());
        entity.setPhone(customer.getPhone());
        return entity;
    }

    public static CustomerDTO mapToDTO(CustomerEntity customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setPhone(customer.getPhone());
        return dto;
    }
}

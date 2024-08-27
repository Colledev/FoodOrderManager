package com.example.foodordermanager.customer;

import com.example.foodordermanager.customer.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO getCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse
                (new CustomerEntity());
        return CustomerMapper.mapToDTO(customerEntity);
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = CustomerMapper.mapToEntity(customerDTO);
        CustomerEntity savedEntity = customerRepository.save(customerEntity);
        return CustomerMapper.mapToDTO(savedEntity);
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = CustomerMapper.mapToEntity(customerDTO);
        CustomerEntity updatedEntity = customerRepository.save(customerEntity);
        return CustomerMapper.mapToDTO(updatedEntity);
    }

    public List<CustomerDTO> getCustomersByName(String name) {
        List<CustomerEntity> customerEntities = customerRepository.findByNameContainingIgnoreCase(name);
        return customerEntities.stream()
                .map(CustomerMapper::mapToDTO)
                .collect(Collectors.toList());
    }

}

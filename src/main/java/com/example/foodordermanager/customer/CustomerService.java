package com.example.foodordermanager.customer;

import com.example.foodordermanager.customer.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public CustomerDTO getCustomerByName(String name) {
        CustomerEntity customerEntity = customerRepository.findByName(name);
        return CustomerMapper.mapToDTO(customerEntity);
    }

//    public CustomerDTO getCustomerByCPF(String CPF) {
//        CustomerEntity customerEntity = customerRepository.findByCPF(CPF);
//        return CustomerMapper.mapToDTO(customerEntity);
//    }
}

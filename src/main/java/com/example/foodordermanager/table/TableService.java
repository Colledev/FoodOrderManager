package com.example.foodordermanager.table;

import com.example.foodordermanager.customer.CustomerEntity;
import com.example.foodordermanager.customer.CustomerRepository;
import com.example.foodordermanager.table.dto.TableDTO;
import com.example.foodordermanager.table.dto.TableStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private CustomerRepository CustomerRepository;

    public TableDTO createTable(TableDTO tableDTO) {
        TableEntity table = TableMapper.mapToTable(tableDTO);
        table.setAvailable(true);
        TableEntity savedTable = tableRepository.save(table);
        return TableMapper.mapToTableDTO(savedTable);
    }

    public void updateTableStatus(TableStatusDTO tableStatusDTO) {
        TableEntity table = tableRepository.findById(tableStatusDTO.getId())
                .orElseThrow(() -> new RuntimeException("Table not found"));

        if (!tableStatusDTO.getAvailable()) {
            CustomerEntity customer = CustomerRepository.findById(tableStatusDTO.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            table.setCustomer(customer);
            table.setAvailable(false);
        } else {
            table.setCustomer(null);
            table.setAvailable(true);
        }

        tableRepository.save(table);
    }



}

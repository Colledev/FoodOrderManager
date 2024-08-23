package com.example.foodordermanager.table;

import com.example.foodordermanager.customer.CustomerEntity;
import com.example.foodordermanager.customer.CustomerRepository;
import com.example.foodordermanager.order.OrderEntity;
import com.example.foodordermanager.order.OrderRepository;
import com.example.foodordermanager.order.OrderStatus;
import com.example.foodordermanager.table.dto.TableDTO;
import com.example.foodordermanager.table.dto.TableStatusDTO;
import com.example.foodordermanager.tablehistory.TableHistoryEntity;
import com.example.foodordermanager.tablehistory.TableHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TableHistoryRepository tableHistoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<TableDTO> getAllTables() {
        List<TableEntity> tables = tableRepository.findAll();
        return tables.stream()
                .map(TableMapper::mapToTableDTO)
                .toList();
    }

    public List<TableDTO> getAllAvailableTable() {
        List<TableEntity> tables = tableRepository.findAllByAvailable(true);
        return tables.stream()
                .map(TableMapper::mapToTableDTO)
                .toList();
    }

    public List<TableDTO> getAllNotAvailableTable() {
        List<TableEntity> tables = tableRepository.findAllByAvailable(false);
        return tables.stream()
                .map(TableMapper::mapToTableDTO)
                .toList();
    }

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
            CustomerEntity customer = customerRepository.findById(tableStatusDTO.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            TableHistoryEntity history = new TableHistoryEntity();
            history.setTable(table);
            history.setCustomer(customer);
            history.setOccupiedAt(LocalDateTime.now());
            history.setReleasedAt(null);
            tableHistoryRepository.save(history);

            table.setAvailable(false);
        } else {
            TableHistoryEntity history = tableHistoryRepository.findByTableAndReleasedAtIsNull(table);

            if (history != null) {
                history.setReleasedAt(LocalDateTime.now());
                tableHistoryRepository.save(history);
            } else {
                System.out.println("No open TableHistory found for table: " + table.getId());
            }

            table.setAvailable(true);
        }
        tableRepository.save(table);
    }
}

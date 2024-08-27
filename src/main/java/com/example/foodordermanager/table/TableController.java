package com.example.foodordermanager.table;

import com.example.foodordermanager.table.dto.TableDTO;
import com.example.foodordermanager.table.dto.TableStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private TableService tableService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('WAITER') or hasRole('CASHIER')")
    @GetMapping
    public ResponseEntity<List<TableDTO>> getAllTables() {
        List<TableDTO> tables = tableService.getAllTables();
        return ResponseEntity.ok(tables);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('WAITER') or hasRole('CASHIER')")
    @GetMapping("/available")
    public ResponseEntity<List<TableDTO>> getAllAvailableTables() {
        List<TableDTO> tables = tableService.getAllAvailableTable();
        return ResponseEntity.ok(tables);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('WAITER') or hasRole('CASHIER')")
    @GetMapping("/not-available")
    public ResponseEntity<List<TableDTO>> getAllNotAvailableTables() {
        List<TableDTO> tables = tableService.getAllNotAvailableTable();
        return ResponseEntity.ok(tables);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<TableDTO> createTable(@RequestBody TableDTO tableDTO) {
        TableDTO createdTable = tableService.createTable(tableDTO);
        return ResponseEntity.ok(createdTable);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('WAITER') or hasRole('CASHIER')")
    @PutMapping("/{id}/status")
    public ResponseEntity<TableStatusDTO> updateTableStatus(@PathVariable Long id, @RequestBody TableStatusDTO tableStatusDTO) {
        tableStatusDTO.setId(id);
        tableService.updateTableStatus(tableStatusDTO);
        return ResponseEntity.ok(tableStatusDTO);
    }
}

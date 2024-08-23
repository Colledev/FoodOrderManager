package com.example.foodordermanager.table;

import com.example.foodordermanager.table.dto.TableDTO;
import com.example.foodordermanager.table.dto.TableStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping
    public ResponseEntity<List<TableDTO>> getAllTables() {
        List<TableDTO> tables = tableService.getAllTables();
        return ResponseEntity.ok(tables);
    }

    @GetMapping("/available")
    public ResponseEntity<List<TableDTO>> getAllAvailableTables() {
        List<TableDTO> tables = tableService.getAllAvailableTable();
        return ResponseEntity.ok(tables);
    }

    @GetMapping("/not-available")
    public ResponseEntity<List<TableDTO>> getAllNotAvailableTables() {
        List<TableDTO> tables = tableService.getAllNotAvailableTable();
        return ResponseEntity.ok(tables);
    }

    @PostMapping("/create")
    public ResponseEntity<TableDTO> createTable(@RequestBody TableDTO tableDTO) {
        TableDTO createdTable = tableService.createTable(tableDTO);
        return ResponseEntity.ok(createdTable);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TableStatusDTO> updateTableStatus(@PathVariable Long id, @RequestBody TableStatusDTO tableStatusDTO) {
        tableStatusDTO.setId(id);
        tableService.updateTableStatus(tableStatusDTO);
        return ResponseEntity.ok(tableStatusDTO);
    }
}

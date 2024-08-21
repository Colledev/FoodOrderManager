package com.example.foodordermanager.table;

import com.example.foodordermanager.table.dto.TableDTO;
import com.example.foodordermanager.table.dto.TableStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private TableService tableService;

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

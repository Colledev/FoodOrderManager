package com.example.foodordermanager.table;

import com.example.foodordermanager.table.dto.TableDTO;
import com.example.foodordermanager.table.dto.TableStatusDTO;

public class TableMapper {

    public static TableDTO mapToTableDTO(TableEntity table) {
        TableDTO dto = new TableDTO();
        dto.setId(table.getId());
        dto.setNumber(table.getNumber());
        dto.setSeats(table.getSeats());
        dto.setAvailable(table.getAvailable());
        return dto;
    }

    public static TableEntity mapToTable(TableDTO table) {
        TableEntity entity = new TableEntity();
        entity.setId(table.getId());
        entity.setNumber(table.getNumber());
        entity.setSeats(table.getSeats());
        return entity;
    }

    public static TableStatusDTO mapToStatusDTO(TableEntity table) {
        TableStatusDTO dto = new TableStatusDTO();
        dto.setId(table.getId());
        dto.setAvailable(table.getAvailable());
        return dto;
    }

    public static TableEntity mapToStatus(TableStatusDTO table) {
        TableEntity entity = new TableEntity();
        entity.setId(table.getId());
        entity.setAvailable(table.getAvailable());
        return entity;
    }


}

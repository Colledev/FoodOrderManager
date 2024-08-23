package com.example.foodordermanager.tablehistory;

import com.example.foodordermanager.table.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableHistoryRepository extends JpaRepository<TableHistoryEntity, Long> {

    TableHistoryEntity findByTableAndReleasedAtIsNull(TableEntity table);
}

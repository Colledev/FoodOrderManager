package com.example.foodordermanager.table;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableRepository extends JpaRepository<TableEntity, Long> {
    Optional<TableEntity> findByNumber(Integer number);

}

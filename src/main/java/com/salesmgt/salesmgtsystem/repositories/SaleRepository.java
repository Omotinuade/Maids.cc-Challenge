package com.salesmgt.salesmgtsystem.repositories;

import com.salesmgt.salesmgtsystem.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, String> {
    List<Sale> findByCreationDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}

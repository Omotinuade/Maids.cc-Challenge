package com.salesmgt.salesmgtsystem.repositories;

import com.salesmgt.salesmgtsystem.models.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, String> {
}

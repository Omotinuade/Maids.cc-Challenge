package com.salesmgt.salesmgtsystem.repositories;

import com.salesmgt.salesmgtsystem.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, String> {
}

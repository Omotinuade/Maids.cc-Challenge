package com.salesmgt.salesmgtsystem.repositories;

import com.salesmgt.salesmgtsystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}

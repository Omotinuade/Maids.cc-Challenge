package com.salesmgt.salesmgtsystem.repositories;

import com.salesmgt.salesmgtsystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByProductName(String productName);
}

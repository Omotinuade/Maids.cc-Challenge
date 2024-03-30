package com.salesmgt.salesmgtsystem.repositories;

import com.salesmgt.salesmgtsystem.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {
    Page<Client> findAllClient( Pageable pageable);

    Optional<Client> findByEmail(String emailAddress);
}

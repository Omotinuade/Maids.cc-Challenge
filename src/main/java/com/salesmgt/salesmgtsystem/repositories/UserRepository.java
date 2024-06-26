package com.salesmgt.salesmgtsystem.repositories;

import com.salesmgt.salesmgtsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String s);
}

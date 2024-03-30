package com.salesmgt.salesmgtsystem.repositories;

import com.salesmgt.salesmgtsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

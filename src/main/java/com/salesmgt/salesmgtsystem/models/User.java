package com.salesmgt.salesmgtsystem.models;

import com.salesmgt.salesmgtsystem.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String username;
    private String password;
    @ElementCollection
    private Set<Role> roles;


}


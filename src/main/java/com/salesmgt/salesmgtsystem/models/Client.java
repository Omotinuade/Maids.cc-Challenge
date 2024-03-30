package com.salesmgt.salesmgtsystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank(message = "Name is required")
    @Size(min=2,message = "Name requires at least 2 characters")
    private String name;

    @NotBlank(message = "Last name is required")
    @Size(min=2,message = "LastName requires at least 2 characters")
    private String lastName;

    @NotBlank(message = "Mobile is required")
    @Pattern(regexp = "\\d{11}", message = "Mobile must be 11 digits")
    private String mobile;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Address is required")
    @Size(min=2,message = "Address requires at least 2 characters")
    private String address;
}


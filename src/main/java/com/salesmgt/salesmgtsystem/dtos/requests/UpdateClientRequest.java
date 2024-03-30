package com.salesmgt.salesmgtsystem.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UpdateClientRequest {
    @NotBlank
    @Size(min = 2, message = "First name should have at least 2 letters.")
    private String name;

    @Size(min = 2, message = "Last name should have at least 2 letters.")
    private String lastName;

    private String mobile;

    @Email
    private String email;
    private String address;
}

package com.salesmgt.salesmgtsystem.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterClientRequest {

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

package com.salesmgt.salesmgtsystem.dtos.responses;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
public class ClientResponse {
    private String id;

    private String name;

    private String lastName;

    private String mobile;

    private String email;

    private String address;
}

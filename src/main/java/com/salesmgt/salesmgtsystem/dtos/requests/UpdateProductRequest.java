package com.salesmgt.salesmgtsystem.dtos.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class UpdateProductRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value = 0, message = "Initial quantity must be non-negative")
    private int quantity;


    @Min(value = 0, message = "Price must be non-negative")
    private double price;

}

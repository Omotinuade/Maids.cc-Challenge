package com.salesmgt.salesmgtsystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterProductRequest {
    private String name;
    private String description;
    private String category;
    private int initialQuantity;
    private double price;
}

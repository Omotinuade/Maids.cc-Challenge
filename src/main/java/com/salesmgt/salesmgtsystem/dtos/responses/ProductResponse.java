package com.salesmgt.salesmgtsystem.dtos.responses;

import com.salesmgt.salesmgtsystem.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
@Builder
@Getter
public class ProductResponse {

    private String id;


    private String name;

    private String description;

    private Category category;

    private int quantity;

    private double price;

    private Date creationDate;
}

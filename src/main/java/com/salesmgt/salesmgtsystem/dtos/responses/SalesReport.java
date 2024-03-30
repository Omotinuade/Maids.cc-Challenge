package com.salesmgt.salesmgtsystem.dtos.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
public class SalesReport {
    private Integer numberOfSales;
    private BigDecimal totalRevenue;
    private Long topSellingProductId;
    private Long topPerformingSellerId;
}

package com.muralis.sistema.controllers.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class ExpenseResponse {
    private Integer id;
    private BigDecimal value;
    private LocalDateTime purchaseDate;
    private String description;
    private Integer companyId;
    private Integer paymentTypeId;
    private Integer categoryId;
    private Integer addressId;
}

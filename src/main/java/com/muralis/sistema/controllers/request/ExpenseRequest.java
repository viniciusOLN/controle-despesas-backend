package com.muralis.sistema.controllers.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseRequest {
    @NotBlank
    private BigDecimal value;
    @NotBlank
    private String description;
    @NotBlank
    private Integer companyId;
    @NotBlank
    private Integer paymentTypeId;
    @NotBlank
    private Integer categoryId;
    @NotBlank
    private Integer addressId;
}


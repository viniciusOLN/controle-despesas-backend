package com.muralis.sistema.controllers.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseRequest {
    @NotNull
    private BigDecimal value;
    @NotBlank
    private String description;
    @NotNull
    private Integer companyId;
    @NotNull
    private Integer paymentTypeId;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer addressId;
}


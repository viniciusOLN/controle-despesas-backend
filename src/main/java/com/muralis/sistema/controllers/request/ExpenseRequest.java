package com.muralis.sistema.controllers.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExpenseRequest {
    @NotNull
    private BigDecimal value;
    @NotBlank
    private String description;
    private LocalDateTime buyDate;
    @NotNull
    private Integer paymentTypeId;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer addressId;
}


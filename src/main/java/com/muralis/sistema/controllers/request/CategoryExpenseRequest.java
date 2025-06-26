package com.muralis.sistema.controllers.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryExpenseRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}

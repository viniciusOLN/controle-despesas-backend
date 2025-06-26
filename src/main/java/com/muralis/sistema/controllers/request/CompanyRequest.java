package com.muralis.sistema.controllers.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyRequest {
    @NotBlank
    private String company;
}

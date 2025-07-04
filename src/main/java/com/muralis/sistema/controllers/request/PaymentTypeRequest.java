package com.muralis.sistema.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PaymentTypeRequest {
    @NotBlank
    private String description;
}

package com.muralis.sistema.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentTypeRequest {
    private String description;
}

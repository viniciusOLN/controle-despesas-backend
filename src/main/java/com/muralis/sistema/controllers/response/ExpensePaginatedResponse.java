package com.muralis.sistema.controllers.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ExpensePaginatedResponse {

    private Long id;
    private BigDecimal value;
    private LocalDateTime buyDate;
    private String description;

    // Tipo de pagamento
    private Long paymentTypeId;
    private String paymentTypeDescription;

    // Categoria
    private Long categoryId;
    private String categoryName;

    // Endereço
    private Long addressId;
    private String state;
    private String city;
    private String zipCode;
    private String district;
    private String street;
    private String number;
    private String complement;
}

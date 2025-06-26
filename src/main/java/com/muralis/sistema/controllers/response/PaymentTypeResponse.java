package com.muralis.sistema.controllers.response;

import lombok.*;

@Data
@Builder
public class PaymentTypeResponse {

    private int id;
    private String type;
}

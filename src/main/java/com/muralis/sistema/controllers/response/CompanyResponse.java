package com.muralis.sistema.controllers.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyResponse {
    private Integer id;
    private String company;
}

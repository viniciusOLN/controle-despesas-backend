package com.muralis.sistema.controllers.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {
    private Integer id;
    private String state;
    private String zipCode;
    private String city;
    private String district;
    private String street;
    private String number;
    private String complement;
}
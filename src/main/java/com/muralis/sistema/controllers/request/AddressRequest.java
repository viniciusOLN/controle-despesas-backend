package com.muralis.sistema.controllers.request;

import lombok.Data;

@Data
public class AddressRequest {
    private String state;
    private String city;
    private String district;
    private String street;
    private String number;
    private String complement;
}

package com.muralis.sistema.controllers.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddressRequest {
    @NotBlank
    private String state;
    @NotBlank
    private String city;
    @NotBlank
    private String district;
    @NotBlank
    private String street;
    @NotBlank
    @Pattern(regexp = "^\\d{1,4}(-\\d{1,4})?$", message = "Favor inserir um número válido.")
    private String number;
    private String complement;
}

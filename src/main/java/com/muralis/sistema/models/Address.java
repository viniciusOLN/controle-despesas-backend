package com.muralis.sistema.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "endereco")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uf")
    private String state;
    @Column(name = "cep")
    private String zipCode;
    @Column(name = "municipio")
    private String city;
    @Column(name = "bairro")
    private String district;
    @Column(name = "logradouro")
    private String street;
    @Column(name = "numero")
    private String number;
    @Column(name = "complemento")
    private String complement;
}

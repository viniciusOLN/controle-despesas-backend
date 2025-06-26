package com.muralis.sistema.models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tipos_pagamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo")
    private String description;
}

package com.muralis.sistema.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "despesas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal valor;

    @Column(name = "data_compra")
    private LocalDateTime buyDate;

    @Column(name = "descricao")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "id_tipo_pagamento")
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoryExpense category;

    @ManyToOne
    @JoinColumn(name = "id_local")
    private Address local;
}

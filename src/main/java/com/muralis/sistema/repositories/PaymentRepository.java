package com.muralis.sistema.repositories;

import com.muralis.sistema.models.PaymentTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentTypes, Long> {
    List<PaymentTypes> findAll();
}

package com.muralis.sistema.repositories;

import com.muralis.sistema.models.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentType, Long> {
    List<PaymentType> findAll();
}

package com.muralis.sistema.repositories;

import com.muralis.sistema.models.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAll();

    @Query(
            value = "SELECT e FROM Expense e " +
                    "JOIN FETCH e.local " +
                    "JOIN FETCH e.paymentType " +
                    "JOIN FETCH e.category " +
                    "WHERE e.buyDate BETWEEN :startDate AND :endDate",
            countQuery = "SELECT COUNT(e) FROM Expense e " +
                    "WHERE e.buyDate BETWEEN :startDate AND :endDate"
    )
    Page<Expense> findByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );


}

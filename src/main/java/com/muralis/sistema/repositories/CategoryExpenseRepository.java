package com.muralis.sistema.repositories;

import com.muralis.sistema.models.CategoryExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryExpenseRepository extends JpaRepository<CategoryExpense, Integer> {
}

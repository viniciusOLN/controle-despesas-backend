package com.muralis.sistema;

import com.muralis.sistema.controllers.response.ExpenseResponse;
import com.muralis.sistema.models.*;
import com.muralis.sistema.repositories.ExpenseRepository;
import com.muralis.sistema.services.ExpenseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @InjectMocks
    private ExpenseService expenseService;

    @Mock
    private ExpenseRepository expenseRepository;

    @Test
    void shouldReturnAllExpenses() {
        Expense expense = Expense.builder()
                .id(1)
                .valor(new BigDecimal("1000.00"))
                .buyDate(LocalDateTime.now())
                .description("Aqui uma descrição da despesa que eu fiz")
                .company(new Company(1, "Empresa de testes"))
                .paymentType(new PaymentType(1L, "Cartão"))
                .category(new CategoryExpense(1, "Alimentação", "Categoria de comidas"))
                .local(new Address(1, "SP", "São Paulo", "Centro", "Rua Edivaldo Fernandes", "50", "Minha casa ali"))
                .build();

        when(expenseRepository.findAll()).thenReturn(List.of(expense));

        List<ExpenseResponse> result = expenseService.getAll();

        assertEquals(1, result.size());
        assertEquals("Taxi", result.get(0).getDescription());
    }
}

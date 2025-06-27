package com.muralis.sistema.services;

import com.muralis.sistema.controllers.request.ExpenseRequest;
import com.muralis.sistema.controllers.response.ExpensePaginatedResponse;
import com.muralis.sistema.controllers.response.ExpenseResponse;
import com.muralis.sistema.exceptions.CustomException;
import com.muralis.sistema.exceptions.DatabaseException;
import com.muralis.sistema.models.*;
import com.muralis.sistema.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    CategoryExpenseRepository categoryExpenseRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PaymentRepository paymentRepository;

    public Page<ExpensePaginatedResponse> getByCurrentMonth(Pageable pageable) {
        LocalDate firstDay = LocalDate.now().withDayOfMonth(1);
        LocalDate lastDay = firstDay.with(TemporalAdjusters.lastDayOfMonth());

        LocalDateTime start = firstDay.atStartOfDay();
        LocalDateTime end = lastDay.atTime(LocalTime.MAX);

        Page<Expense> page = expenseRepository.findByDateRange(start, end, pageable);

        return page.map(this::toResponseWithRelations);
    }

    public List<ExpenseResponse> getAll() {
        try {
            return expenseRepository.findAll().stream()
                    .map(this::toResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar despesas no banco de dados." + e.getMessage());
        }
    }

    public ExpenseResponse getById(Long id) throws ChangeSetPersister.NotFoundException {
        Expense e = expenseRepository.findById(id)
                .orElseThrow(() -> new CustomException("Despesa não encontrada. Por favor utilize um id válido."));
        return toResponse(e);
    }

    public Integer create(ExpenseRequest request) throws ChangeSetPersister.NotFoundException {
        Expense expense = toEntity(request);
        expense.setBuyDate(LocalDateTime.now());
        try{
            return expenseRepository.save(expense).getId();
        }catch (Exception e){
            throw new DatabaseException("Erro ao criar despesa no banco de dados." + e.getMessage());
        }
    }

    public void update(Long id, ExpenseRequest request) throws ChangeSetPersister.NotFoundException {
        Expense e = expenseRepository.findById(id)
                .orElseThrow(() -> new CustomException("Despesa não encontrada. Por favor utilize um id válido."));

        CategoryExpense category = categoryExpenseRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CustomException("Categoria não encontrada. Por favor utilize um id válido."));

        Address address = addressRepository.findById(Long.valueOf(request.getAddressId()))
                .orElseThrow(() -> new CustomException("Endereço não encontrado. Por favor utilize um id válido."));

        PaymentType paymentType = paymentRepository.findById(Long.valueOf(request.getPaymentTypeId()))
                        .orElseThrow();

        e.setValor(request.getValue());
        e.setBuyDate(LocalDateTime.parse(request.getDescription()));
        e.setCategory(category);
        e.setLocal(address);
        e.setPaymentType(paymentType);

        try{
            expenseRepository.save(e);
        }catch (Exception error){
            throw new DatabaseException("Erro ao atualizar despesa no banco de dados." + error.getMessage());
        }
    }

    public void delete(Long id) throws ChangeSetPersister.NotFoundException {
        Expense e = expenseRepository.findById(id)
                .orElseThrow(() -> new CustomException("Despesa não encontrada. Por favor utilize um id válido."));

        try{
            expenseRepository.delete(e);
        }catch (Exception error){
            throw new DatabaseException("Erro ao deletar despesa no banco de dados." + error.getMessage());
        }
    }

    private Expense toEntity(ExpenseRequest r) throws ChangeSetPersister.NotFoundException {
        CategoryExpense category = categoryExpenseRepository.findById(r.getCategoryId())
                .orElseThrow(() -> new CustomException("Categoria não encontrada. Por favor utilize um id válido."));

        Address address = addressRepository.findById(Long.valueOf(r.getAddressId()))
                .orElseThrow(() -> new CustomException("Endereço não encontrado. Por favor utilize um id válido."));

        PaymentType paymentType = paymentRepository.findById(Long.valueOf(r.getPaymentTypeId()))
                .orElseThrow(() -> new CustomException("Tipo de Pagamento não encontrado. Por favor utilize um id válido."));

        return Expense.builder()
                .valor(r.getValue())
                .description(r.getDescription())
                .category(category)
                .local(address)
                .paymentType(paymentType)
                .build();
    }

    private ExpenseResponse toResponse(Expense e) {
        return ExpenseResponse.builder()
                .id(Math.toIntExact(Long.valueOf(e.getId())))
                .value(e.getValor())
                .description(e.getDescription())
                .purchaseDate(e.getBuyDate())
                .categoryId(e.getCategory().getId())
                .addressId(e.getLocal().getId())
                .paymentTypeId(Math.toIntExact(e.getPaymentType().getId()))
                .build();
    }

    private ExpensePaginatedResponse toResponseWithRelations(Expense expense) {
        return ExpensePaginatedResponse.builder()
                .id(Long.valueOf(expense.getId()))
                .value(expense.getValor())
                .buyDate(expense.getBuyDate())
                .description(expense.getDescription())

                .paymentTypeId(expense.getPaymentType().getId())
                .paymentTypeDescription(expense.getPaymentType().getDescription())

                .categoryId(Long.valueOf(expense.getCategory().getId()))
                .categoryName(expense.getCategory().getName())

                .addressId(Long.valueOf(expense.getLocal().getId()))
                .state(expense.getLocal().getState())
                .city(expense.getLocal().getCity())
                .district(expense.getLocal().getDistrict())
                .street(expense.getLocal().getStreet())
                .number(expense.getLocal().getNumber())
                .complement(expense.getLocal().getComplement())

                .build();
    }
}

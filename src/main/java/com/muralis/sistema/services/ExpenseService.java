package com.muralis.sistema.services;

import com.muralis.sistema.controllers.request.ExpenseRequest;
import com.muralis.sistema.controllers.response.CompanyResponse;
import com.muralis.sistema.controllers.response.ExpenseResponse;
import com.muralis.sistema.exceptions.CustomException;
import com.muralis.sistema.exceptions.DatabaseException;
import com.muralis.sistema.models.*;
import com.muralis.sistema.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CategoryExpenseRepository categoryExpenseRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PaymentRepository paymentRepository;

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

        Company company = companyRepository.findById(Long.valueOf(request.getCompanyId()))
                .orElseThrow(() -> new CustomException("Empresa não encontrada. Por favor utilize um id válido."));

        CategoryExpense category = categoryExpenseRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CustomException("Categoria não encontrada. Por favor utilize um id válido."));

        Address address = addressRepository.findById(Long.valueOf(request.getAddressId()))
                .orElseThrow(() -> new CustomException("Endereço não encontrado. Por favor utilize um id válido."));

        PaymentType paymentType = paymentRepository.findById(Long.valueOf(request.getPaymentTypeId()))
                        .orElseThrow();

        e.setValor(request.getValue());
        e.setBuyDate(LocalDateTime.parse(request.getDescription()));
        e.setCompany(company);
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
        Company company = companyRepository.findById(Long.valueOf(r.getCompanyId()))
                .orElseThrow(() -> new CustomException("Empresa não encontrada. Por favor utilize um id válido."));

        CategoryExpense category = categoryExpenseRepository.findById(r.getCategoryId())
                .orElseThrow(() -> new CustomException("Categoria não encontrada. Por favor utilize um id válido."));

        Address address = addressRepository.findById(Long.valueOf(r.getAddressId()))
                .orElseThrow(() -> new CustomException("Endereço não encontrado. Por favor utilize um id válido."));

        PaymentType paymentType = paymentRepository.findById(Long.valueOf(r.getPaymentTypeId()))
                .orElseThrow(() -> new CustomException("Tipo de Pagamento não encontrado. Por favor utilize um id válido."));

        return Expense.builder()
                .valor(r.getValue())
                .description(r.getDescription())
                .company(company)
                .category(category)
                .local(address)
                .paymentType(paymentType)
                .build();
    }

    private ExpenseResponse toResponse(Expense e) {
        return ExpenseResponse.builder()
                .id(e.getId())
                .value(e.getValor())
                .description(e.getDescription())
                .purchaseDate(e.getBuyDate())
                .companyId(e.getCompany().getId())
                .categoryId(e.getCategory().getId())
                .addressId(e.getLocal().getId())
                .paymentTypeId(Math.toIntExact(e.getPaymentType().getId()))
                .build();
    }
}

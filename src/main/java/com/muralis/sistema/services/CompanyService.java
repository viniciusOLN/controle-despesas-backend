package com.muralis.sistema.services;

import com.muralis.sistema.controllers.request.CategoryExpenseRequest;
import com.muralis.sistema.controllers.request.CompanyRequest;
import com.muralis.sistema.controllers.response.CategoryExpenseResponse;
import com.muralis.sistema.controllers.response.CompanyResponse;
import com.muralis.sistema.exceptions.CustomException;
import com.muralis.sistema.exceptions.DatabaseException;
import com.muralis.sistema.models.CategoryExpense;
import com.muralis.sistema.models.Company;
import com.muralis.sistema.repositories.CategoryExpenseRepository;
import com.muralis.sistema.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<CompanyResponse> getAll() {
        try {
            return companyRepository.findAll().stream()
                    .map(this::toResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar as empresas." + e.getMessage());
        }
    }

    public CompanyResponse getById(Long id) throws ChangeSetPersister.NotFoundException {
        Company company = companyRepository.findById(id)
                .orElseThrow(() ->  new CustomException("Empresa não encontrada. Por favor utilize um id válido."));
        return toResponse(company);
    }

    public Integer create(CompanyRequest request) {
        try{
            Company company = toEntity(request);
            Company saved = companyRepository.save(company);
            return saved.getId();
        }catch (Exception e){
            throw new DatabaseException("Erro ao tentar criar empresa no banco de dados. " + e.getMessage());
        }
    }

    public void update(Long id, CompanyRequest request) throws ChangeSetPersister.NotFoundException {
        Company category = companyRepository.findById(id)
                .orElseThrow(() -> new CustomException("Empresa não encontrada. Por favor utilize um id válido."));

        category.setCompany(request.getCompany());
        try{
            companyRepository.save(category);
        }catch (Exception e){
            throw new DatabaseException("Erro ao tentar atualizar empresa no banco de dados. " + e.getMessage());
        }
    }

    public void delete(Long id) throws ChangeSetPersister.NotFoundException {
        Company category = companyRepository.findById(id)
                .orElseThrow(() -> new CustomException("Empresa não encontrada. Por favor utilize um id válido."));

        try{
            companyRepository.delete(category);
        }catch (Exception e){
            throw new DatabaseException("Erro ao tentar deletar empresa no banco de dados. " + e.getMessage());
        }
    }

    private Company toEntity(CompanyRequest request) {
        return Company.builder()
                .company(request.getCompany())
                .build();
    }

    private CompanyResponse toResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .company(company.getCompany())
                .build();
    }
}

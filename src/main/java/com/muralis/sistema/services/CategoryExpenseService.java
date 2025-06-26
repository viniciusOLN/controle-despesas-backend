package com.muralis.sistema.services;

import com.muralis.sistema.controllers.request.CategoryExpenseRequest;
import com.muralis.sistema.controllers.response.CategoryExpenseResponse;
import com.muralis.sistema.exceptions.CustomException;
import com.muralis.sistema.exceptions.DatabaseException;
import com.muralis.sistema.models.Address;
import com.muralis.sistema.models.CategoryExpense;
import com.muralis.sistema.repositories.CategoryExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryExpenseService {

    @Autowired
    private CategoryExpenseRepository categoryRepository;

    public List<CategoryExpenseResponse> getAll() {
        try {
            return categoryRepository.findAll().stream()
                    .map(this::toResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar as categorias." + e.getMessage());
        }
    }

    public CategoryExpenseResponse getById(Integer id) throws ChangeSetPersister.NotFoundException {
        CategoryExpense category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Categoria não encontrada. Por favor utilize um id válido."));
        return toResponse(category);
    }

    public Integer create(CategoryExpenseRequest request) {
        try{
            CategoryExpense category = toEntity(request);
            CategoryExpense saved = categoryRepository.save(category);
            return saved.getId();
        }catch (Exception e){
            throw new DatabaseException("Erro ao tentar criar categoria. " + e.getMessage());
        }
    }

    public void update(Integer id, CategoryExpenseRequest request) throws ChangeSetPersister.NotFoundException {
        CategoryExpense category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Categoria não encontrada. Por favor utilize um id válido."));

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        try{
            categoryRepository.save(category);
        }catch (Exception e){
            throw new DatabaseException("Erro ao tentar atualizar categoria. " + e.getMessage());
        }
    }

    public void delete(Integer id) throws ChangeSetPersister.NotFoundException {
        CategoryExpense category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Categoria não encontrada. Por favor utilize um id válido."));
        try{
            categoryRepository.delete(category);
        }catch (Exception e){
            throw new DatabaseException("Erro ao tentar deletar categoria. " + e.getMessage());
        }
    }

    private CategoryExpense toEntity(CategoryExpenseRequest request) {
        return CategoryExpense.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    private CategoryExpenseResponse toResponse(CategoryExpense category) {
        return CategoryExpenseResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}

package com.muralis.sistema.controllers.v1;

import com.muralis.sistema.config.RestConfig;
import com.muralis.sistema.controllers.request.CategoryExpenseRequest;
import com.muralis.sistema.controllers.response.CategoryExpenseResponse;
import com.muralis.sistema.controllers.response.ResponseBase;
import com.muralis.sistema.services.CategoryExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestConfig.API_BASE + "/categorias")
public class CategoryExpensesController {

    @Autowired
    private CategoryExpenseService categoryService;

    @GetMapping
    public ResponseEntity<ResponseBase<List<CategoryExpenseResponse>>> getAllCategories() {
        return ResponseEntity.ok(ResponseBase.ok(categoryService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<CategoryExpenseResponse>> getCategoryById(@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(ResponseBase.ok(categoryService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseBase<Integer>> createCategory(@RequestBody @Valid CategoryExpenseRequest request) {
        return ResponseEntity.ok(ResponseBase.ok(categoryService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<String>> updateCategory(@PathVariable Integer id, @RequestBody @Valid CategoryExpenseRequest request) throws ChangeSetPersister.NotFoundException {
        categoryService.update(id, request);
        return ResponseEntity.ok(ResponseBase.ok("Categoria atualizada com sucesso!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase<String>> deleteCategory(@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        categoryService.delete(id);
        return ResponseEntity.ok(ResponseBase.ok("Categoria deletada com sucesso!"));
    }
}

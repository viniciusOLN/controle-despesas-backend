package com.muralis.sistema.controllers.v1;

import com.muralis.sistema.config.RestConfig;
import com.muralis.sistema.controllers.request.CategoryExpenseRequest;
import com.muralis.sistema.controllers.response.CategoryExpenseResponse;
import com.muralis.sistema.controllers.response.ResponseBase;
import com.muralis.sistema.services.CategoryExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestConfig.API_BASE + "/categorias")
@Tag(name = "Categorias", description = "Categorias de pagamento")
public class CategoryExpensesController {

    @Autowired
    private CategoryExpenseService categoryService;

    @GetMapping
    @Operation(summary = "Pega todas as categorias")
    public ResponseEntity<ResponseBase<List<CategoryExpenseResponse>>> getAllCategories() {
        return ResponseEntity.ok(ResponseBase.ok(categoryService.getAll()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Pega a categoria por id")
    public ResponseEntity<ResponseBase<CategoryExpenseResponse>> getCategoryById(@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(ResponseBase.ok(categoryService.getById(id)));
    }

    @PostMapping
    @Operation(summary = "Cria uma nova categoria")
    public ResponseEntity<ResponseBase<Integer>> createCategory(@RequestBody @Valid CategoryExpenseRequest request) {
        return ResponseEntity.ok(ResponseBase.ok(categoryService.create(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma categoria")
    public ResponseEntity<ResponseBase<String>> updateCategory(@PathVariable Integer id, @RequestBody @Valid CategoryExpenseRequest request) throws ChangeSetPersister.NotFoundException {
        categoryService.update(id, request);
        return ResponseEntity.ok(ResponseBase.ok("Categoria atualizada com sucesso!"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma categoria")
    public ResponseEntity<ResponseBase<String>> deleteCategory(@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        categoryService.delete(id);
        return ResponseEntity.ok(ResponseBase.ok("Categoria deletada com sucesso!"));
    }
}

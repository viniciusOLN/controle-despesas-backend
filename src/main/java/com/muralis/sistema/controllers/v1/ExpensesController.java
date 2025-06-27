package com.muralis.sistema.controllers.v1;

import com.muralis.sistema.config.RestConfig;
import com.muralis.sistema.controllers.request.ExpenseRequest;
import com.muralis.sistema.controllers.response.ExpensePaginatedResponse;
import com.muralis.sistema.controllers.response.ExpenseResponse;
import com.muralis.sistema.controllers.response.ResponseBase;
import com.muralis.sistema.services.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestConfig.API_BASE)
@Tag(name = "Despesas", description = "Gerenciamento das despesas")
public class ExpensesController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    @Operation(summary = "Pega todas as despesas")
    public ResponseEntity<ResponseBase<List<ExpenseResponse>>> getAll() {
        return ResponseEntity.ok(ResponseBase.ok(expenseService.getAll()));
    }

    @GetMapping("/by-company-month")
    @Operation(summary = "Pega todas as despesas do mês atual de uma empresa, com paginação")
    public ResponseEntity<ResponseBase<List<ExpensePaginatedResponse>>> getByCompany(
            @RequestParam Long companyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ExpensePaginatedResponse> pageResult = expenseService.getByCompanyCurrentMonth(companyId, pageable);

        List<ExpensePaginatedResponse> list = pageResult.getContent();
        return ResponseEntity.ok(ResponseBase.ok(list));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Pega uma despesa pelo ID")
    public ResponseEntity<ResponseBase<ExpenseResponse>> getById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(ResponseBase.ok(expenseService.getById(id)));
    }

    @PostMapping
    @Operation(summary = "Cria uma nova despesa")
    public ResponseEntity<ResponseBase<Integer>> create(@RequestBody @Valid ExpenseRequest request) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(ResponseBase.ok(expenseService.create(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma despesa existente")
    public ResponseEntity<ResponseBase<String>> update(@PathVariable Long id, @RequestBody @Valid ExpenseRequest request) throws ChangeSetPersister.NotFoundException {
        expenseService.update(id, request);
        return ResponseEntity.ok(ResponseBase.ok("Despesa atualizada com sucesso"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma despesa")
    public ResponseEntity<ResponseBase<String>> delete(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        expenseService.delete(id);
        return ResponseEntity.ok(ResponseBase.ok("Despesa deletada com sucesso"));
    }
}

package com.muralis.sistema.controllers.v1;

import com.muralis.sistema.config.RestConfig;
import com.muralis.sistema.controllers.request.ExpenseRequest;
import com.muralis.sistema.controllers.response.ExpenseResponse;
import com.muralis.sistema.controllers.response.ResponseBase;
import com.muralis.sistema.services.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestConfig.API_BASE)
public class ExpensesController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<ResponseBase<List<ExpenseResponse>>> getAll() {
        return ResponseEntity.ok(ResponseBase.ok(expenseService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<ExpenseResponse>> getById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(ResponseBase.ok(expenseService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseBase<Integer>> create(@RequestBody @Valid ExpenseRequest request) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(ResponseBase.ok(expenseService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<String>> update(@PathVariable Long id, @RequestBody @Valid ExpenseRequest request) throws ChangeSetPersister.NotFoundException {
        expenseService.update(id, request);
        return ResponseEntity.ok(ResponseBase.ok("Expense updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase<String>> delete(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        expenseService.delete(id);
        return ResponseEntity.ok(ResponseBase.ok("Expense deleted successfully"));
    }
}

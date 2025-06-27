package com.muralis.sistema.controllers.v1;

import com.muralis.sistema.config.RestConfig;
import com.muralis.sistema.controllers.request.CompanyRequest;
import com.muralis.sistema.controllers.response.CompanyResponse;
import com.muralis.sistema.controllers.response.ResponseBase;
import com.muralis.sistema.services.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestConfig.API_BASE + "/empresas")
@Tag(name = "Empresas", description = "Gerenciamento das empreas")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    @Operation(summary = "Pega todas as empresas cadastradas")
    public ResponseEntity<ResponseBase<List<CompanyResponse>>> getAll() {
        return ResponseEntity.ok(ResponseBase.ok(companyService.getAll()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Pega uma empresa por id")
    public ResponseEntity<ResponseBase<CompanyResponse>> getById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(ResponseBase.ok(companyService.getById(id)));
    }

    @PostMapping
    @Operation(summary = "Cria uma nova empresa")
    public ResponseEntity<ResponseBase<Integer>> create(@RequestBody @Valid CompanyRequest request) {
        return ResponseEntity.ok(ResponseBase.ok(companyService.create(request)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma empresa por id")
    public ResponseEntity<ResponseBase<String>> update(@PathVariable Long id, @RequestBody @Valid CompanyRequest request) throws ChangeSetPersister.NotFoundException {
        companyService.update(id, request);
        return ResponseEntity.ok(ResponseBase.ok("Empresa atualizada com sucesso."));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma empresa")
    public ResponseEntity<ResponseBase<String>> delete(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        companyService.delete(id);
        return ResponseEntity.ok(ResponseBase.ok("Empresa deletada com sucesso."));
    }
}

package com.muralis.sistema.controllers.v1;

import com.muralis.sistema.config.RestConfig;
import com.muralis.sistema.controllers.request.PaymentTypeRequest;
import com.muralis.sistema.controllers.response.PaymentTypeResponse;
import com.muralis.sistema.controllers.response.ResponseBase;
import com.muralis.sistema.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = RestConfig.API_BASE + "/tipo-pagamentos")
@Tag(name = "Tipos de pagamento", description = "Endpoints para gerenciamento de tipos de pagamento")
public class PaymentTypeController {

    @Autowired
    public PaymentService paymentService;

    PaymentTypeController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @Operation(summary = "Listar todos os tipos de pagamento")
    @GetMapping
    public ResponseEntity<ResponseBase<List<PaymentTypeResponse>>> getAllPaymentTypes() throws Exception {
        return ResponseEntity.ok(ResponseBase.ok(paymentService.getAllPaymentTypes()));
    }

    @Operation(summary = "Buscar tipo de pagamento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<PaymentTypeResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseBase.ok(paymentService.getById(id)));
    }

    @Operation(summary = "Criar novo tipo de pagamento")
    @PostMapping
    public ResponseEntity<ResponseBase<String>> create(@RequestBody @Valid PaymentTypeRequest request) {
        paymentService.create(request);
        return ResponseEntity.ok(ResponseBase.ok("Tipo de pagamento criado com sucesso!"));
    }

    @Operation(summary = "Atualizar tipo de pagamento existente")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<String>> update(@PathVariable Long id, @RequestBody @Valid PaymentTypeRequest request) {
        paymentService.update(id, request);
        return ResponseEntity.ok(ResponseBase.ok("Tipo de pagamento atualizado com sucesso!"));
    }

    @Operation(summary = "Deletar tipo de pagamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase<String>> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.ok(ResponseBase.ok("Tipo de pagamento deletado com sucesso!"));
    }
}

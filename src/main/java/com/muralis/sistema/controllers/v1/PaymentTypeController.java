package com.muralis.sistema.controllers.v1;

import com.muralis.sistema.config.RestConfig;
import com.muralis.sistema.controllers.request.PaymentTypeRequest;
import com.muralis.sistema.controllers.response.PaymentTypeResponse;
import com.muralis.sistema.controllers.response.ResponseBase;
import com.muralis.sistema.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = RestConfig.API_BASE + "/tipo-pagamentos")
public class PaymentTypeController {

    @Autowired
    public PaymentService paymentService;

    PaymentTypeController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<ResponseBase<List<PaymentTypeResponse>>> getAllPaymentTypes() throws Exception {
        return ResponseEntity.ok(ResponseBase.ok(paymentService.getAllPaymentTypes()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<PaymentTypeResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseBase.ok(paymentService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseBase<String>> create(@RequestBody @Valid PaymentTypeRequest request) {
        paymentService.create(request);
        return ResponseEntity.ok(ResponseBase.ok("Tipo de pagamento criado com sucesso!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<String>> update(@PathVariable Long id, @RequestBody @Valid PaymentTypeRequest request) {
        paymentService.update(id, request);
        return ResponseEntity.ok(ResponseBase.ok("Tipo de pagamento atualizado com sucesso!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase<String>> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.ok(ResponseBase.ok("Tipo de pagamento deletado com sucesso!"));
    }

}

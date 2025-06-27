package com.muralis.sistema.controllers.v1;

import com.muralis.sistema.config.RestConfig;
import com.muralis.sistema.controllers.request.AddressRequest;
import com.muralis.sistema.controllers.request.PaymentTypeRequest;
import com.muralis.sistema.controllers.response.AddressResponse;
import com.muralis.sistema.controllers.response.PaymentTypeResponse;
import com.muralis.sistema.controllers.response.ResponseBase;
import com.muralis.sistema.services.AddressService;
import com.muralis.sistema.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = RestConfig.API_BASE + "/enderecos")
@Tag(name = "Endereço", description = "Endereço")
public class AddressController {

    @Autowired
    public AddressService addressService;

    AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @Operation(summary = "Pega todos os endereços")
    @GetMapping
    public ResponseEntity<ResponseBase<List<AddressResponse>>> getAllAddresses() {
        return ResponseEntity.ok(ResponseBase.ok(addressService.getAll()));
    }

    @Operation(summary = "Busca endereço por id")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<AddressResponse>> getAdressById(@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(ResponseBase.ok(addressService.getById(id)));
    }

    @Operation(summary = "Criar endereço")
    @PostMapping
    public ResponseEntity<ResponseBase<Integer>> createAddress(@RequestBody @Valid AddressRequest request) {
        return ResponseEntity.ok(ResponseBase.ok(addressService.create(request)));
    }

    @Operation(summary = "Atualizar endereço")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<String>> updateAddress(@PathVariable Integer id, @RequestBody @Valid AddressRequest request) throws ChangeSetPersister.NotFoundException {
        addressService.update(id, request);
        return ResponseEntity.ok(ResponseBase.ok("Endereço atualizado com sucesso!"));
    }

    @Operation(summary = "Deletar endereço")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase<String>> deleteAdress(@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        addressService.delete(id);
        return ResponseEntity.ok(ResponseBase.ok("Endereço deletado com sucesso!"));
    }

}

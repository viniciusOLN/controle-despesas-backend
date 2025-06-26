package com.muralis.sistema.controllers.v1;

import com.muralis.sistema.config.RestConfig;
import com.muralis.sistema.controllers.request.AddressRequest;
import com.muralis.sistema.controllers.request.PaymentTypeRequest;
import com.muralis.sistema.controllers.response.AddressResponse;
import com.muralis.sistema.controllers.response.PaymentTypeResponse;
import com.muralis.sistema.controllers.response.ResponseBase;
import com.muralis.sistema.services.AddressService;
import com.muralis.sistema.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = RestConfig.API_BASE + "/enderecos")
public class AddressController {

    @Autowired
    public AddressService addressService;

    AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<ResponseBase<List<AddressResponse>>> getAll() {
        return ResponseEntity.ok(ResponseBase.ok(addressService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<AddressResponse>> getById(@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(ResponseBase.ok(addressService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseBase<Integer>> create(@RequestBody AddressRequest request) {
        return ResponseEntity.ok(ResponseBase.ok(addressService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBase<String>> update(@PathVariable Integer id, @RequestBody AddressRequest request) throws ChangeSetPersister.NotFoundException {
        addressService.update(id, request);
        return ResponseEntity.ok(ResponseBase.ok("Endereço atualizado com sucesso!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBase<String>> delete(@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        addressService.delete(id);
        return ResponseEntity.ok(ResponseBase.ok("Endereço deletado com sucesso!"));
    }

}

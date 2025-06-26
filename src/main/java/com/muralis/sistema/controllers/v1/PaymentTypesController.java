package com.muralis.sistema.controllers.v1;

import com.muralis.sistema.config.RestConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = RestConfig.API_BASE + "/payments")
public class PaymentTypesController {

    @GetMapping()
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("ola");
    }
}

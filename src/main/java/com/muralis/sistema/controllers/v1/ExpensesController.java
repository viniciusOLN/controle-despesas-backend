package com.muralis.sistema.controllers.v1;

import com.muralis.sistema.config.RestConfig;
import com.muralis.sistema.controllers.response.PaymentTypeResponse;
import com.muralis.sistema.controllers.response.ResponseBase;
import com.muralis.sistema.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = RestConfig.API_BASE)
public class ExpensesController {

    @Autowired
    public PaymentService paymentService;

    ExpensesController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping()
    public ResponseEntity<ResponseBase<List<PaymentTypeResponse>>> hello(){
        List<PaymentTypeResponse> list = new ArrayList<>();
        return ResponseEntity.ok(ResponseBase.ok(list));
    }
}

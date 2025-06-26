package com.muralis.sistema.services;

import com.muralis.sistema.controllers.request.PaymentTypeRequest;
import com.muralis.sistema.controllers.response.PaymentTypeResponse;
import com.muralis.sistema.models.PaymentTypes;
import com.muralis.sistema.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    public List<PaymentTypeResponse> getAllPaymentTypes() throws Exception {
        List<PaymentTypes> paymentTypes = paymentRepository.findAll();

        if(paymentTypes.isEmpty()){
            throw new Exception("nenhum dado cadastrado");
        }

        return paymentTypes.stream()
                .map(paymentTypesItem -> PaymentTypeResponse.builder()
                        .id(Math.toIntExact(paymentTypesItem.getId()))
                        .type(paymentTypesItem.getDescription())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public PaymentTypeResponse getById(Long id) {
        PaymentTypes payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de pagamento não encontrado"));

        return PaymentTypeResponse.builder()
                .id(payment.getId().intValue())
                .type(payment.getDescription())
                .build();
    }

    public Long create(PaymentTypeRequest request) {
        PaymentTypes payment = new PaymentTypes();
        payment.setDescription(request.getDescription());

        PaymentTypes saved = paymentRepository.save(payment);

        return saved.getId();
    }

    public void update(Long id, PaymentTypeRequest request) {
        PaymentTypes existing = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de pagamento não encontrado"));

        existing.setDescription(request.getDescription());

        PaymentTypes updated = paymentRepository.save(existing);
    }

    public void delete(Long id) {
        PaymentTypes existing = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de pagamento não encontrado"));

        paymentRepository.delete(existing);
    }
}

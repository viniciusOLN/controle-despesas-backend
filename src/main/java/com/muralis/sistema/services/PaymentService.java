package com.muralis.sistema.services;

import com.muralis.sistema.controllers.request.PaymentTypeRequest;
import com.muralis.sistema.controllers.response.PaymentTypeResponse;
import com.muralis.sistema.exceptions.CustomException;
import com.muralis.sistema.models.PaymentType;
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
        List<PaymentType> paymentTypes = paymentRepository.findAll();

        if(paymentTypes.isEmpty()){
            throw new CustomException("Nenhum dado cadastrado");
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
        PaymentType payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de pagamento não encontrado"));

        return PaymentTypeResponse.builder()
                .id(payment.getId().intValue())
                .type(payment.getDescription())
                .build();
    }

    public Long create(PaymentTypeRequest request) {
        PaymentType payment = new PaymentType();
        payment.setDescription(request.getDescription());

        PaymentType saved = paymentRepository.save(payment);

        return saved.getId();
    }

    public void update(Long id, PaymentTypeRequest request) {
        PaymentType existing = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de pagamento não encontrado"));

        existing.setDescription(request.getDescription());

        PaymentType updated = paymentRepository.save(existing);
    }

    public void delete(Long id) {
        PaymentType existing = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de pagamento não encontrado"));

        paymentRepository.delete(existing);
    }
}

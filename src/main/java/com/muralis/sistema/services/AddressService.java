package com.muralis.sistema.services;

import com.muralis.sistema.controllers.request.AddressRequest;
import com.muralis.sistema.controllers.request.PaymentTypeRequest;
import com.muralis.sistema.controllers.response.AddressResponse;
import com.muralis.sistema.controllers.response.PaymentTypeResponse;
import com.muralis.sistema.models.Address;
import com.muralis.sistema.models.PaymentTypes;
import com.muralis.sistema.repositories.AddressRepository;
import com.muralis.sistema.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    AddressService(AddressRepository addressRepository){
        this.addressRepository =  addressRepository;
    }

    public List<AddressResponse> getAll() {
        return addressRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AddressResponse getById(Integer id) throws ChangeSetPersister.NotFoundException {
        Address address = addressRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return toResponse(address);
    }

    public Integer create(AddressRequest request) {
        Address address = toEntity(request);
        Address saved = addressRepository.save(address);
        return saved.getId();
    }

    public AddressResponse update(Integer id, AddressRequest request) throws ChangeSetPersister.NotFoundException {
        Address existing = addressRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        existing.setState(request.getState());
        existing.setCity(request.getCity());
        existing.setDistrict(request.getDistrict());
        existing.setState(request.getStreet());
        existing.setNumber(request.getNumber());
        existing.setComplement(request.getComplement());

        Address updated = addressRepository.save(existing);
        return toResponse(updated);
    }

    public void delete(Integer id) throws ChangeSetPersister.NotFoundException {
        Address address = addressRepository.findById(Long.valueOf(id))
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        addressRepository.delete(address);
    }


    private Address toEntity(AddressRequest request) {
        return Address.builder()
                .state(request.getState())
                .city(request.getCity())
                .district(request.getDistrict())
                .street(request.getStreet())
                .number(request.getNumber())
                .complement(request.getComplement())
                .build();
    }

    private AddressResponse toResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .state(address.getState())
                .city(address.getCity())
                .district(address.getDistrict())
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .build();
    }
}

package com.muralis.sistema.services;

import com.muralis.sistema.controllers.request.AddressRequest;
import com.muralis.sistema.controllers.response.AddressResponse;
import com.muralis.sistema.exceptions.CustomException;
import com.muralis.sistema.exceptions.DatabaseException;
import com.muralis.sistema.models.Address;
import com.muralis.sistema.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public boolean addressExists(AddressRequest request) {
        try{
            return addressRepository.findExactMatch(
                    request.getState(),
                    request.getZipCode(),
                    request.getCity(),
                    request.getDistrict(),
                    request.getStreet(),
                    request.getNumber(),
                    request.getComplement()
            ).isPresent();
        }catch (Exception e){
            throw new DatabaseException("Erro ao buscar endereço." + e.getMessage());
        }
    }


    public List<AddressResponse> getAll() {
        try {
            return addressRepository.findAll().stream()
                    .map(this::toResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar endereços." + e.getMessage());
        }
    }

    public AddressResponse getById(Integer id) throws CustomException {
        Address address = addressRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new CustomException("Endereço não encontrado. Por favor utilize um id válido."));
        return toResponse(address);
    }

    public Integer create(AddressRequest request) {

        Address address = toEntity(request);
        try{
            Address saved = addressRepository.save(address);
            return saved.getId();
        }catch (Exception e){
            throw new DatabaseException("Erro ao tentar criar endereço. " + e.getMessage());
        }
    }

    public AddressResponse update(Integer id, AddressRequest request) throws CustomException {
        Address existing = addressRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new CustomException("Endereço não encontrado."));

        System.out.println(request.toString());

        existing.setState(request.getState());
        existing.setCity(request.getCity());
        existing.setZipCode(request.getZipCode());
        existing.setDistrict(request.getDistrict());
        existing.setStreet(request.getStreet());
        existing.setNumber(request.getNumber());
        existing.setComplement(request.getComplement());

        try{
            Address updated = addressRepository.save(existing);
            return toResponse(updated);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao tentar atualizar endereço. " + e.getMessage());
        }
    }

    public void delete(Integer id) throws CustomException {
        Address address = addressRepository.findById(Long.valueOf(id))
                .orElseThrow( () -> new CustomException("Endereço não encontrado."));
        try{
            addressRepository.delete(address);
        }catch (Exception e) {
            throw new DatabaseException("Erro ao tentar deletar endereço. " + e.getMessage());
        }
    }


    private Address toEntity(AddressRequest request) {
        return Address.builder()
                .state(request.getState())
                .zipCode(request.getZipCode())
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
                .zipCode(address.getZipCode())
                .state(address.getState())
                .city(address.getCity())
                .district(address.getDistrict())
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .build();
    }
}

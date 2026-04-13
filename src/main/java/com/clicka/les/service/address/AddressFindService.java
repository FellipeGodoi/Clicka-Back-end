package com.clicka.les.service.address;

import com.clicka.les.dto.address.*;
import com.clicka.les.entity.Address;
import com.clicka.les.repository.user.AddressRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressFindService {

    private final AddressRepository addressRepository;

    //  Buscar por usuário
    public List<AddressListDTO> findByUser(UUID userId) {
        return addressRepository.findByUserId(userId)
                .stream()
                .map(this::toListDTO)
                .collect(Collectors.toList());
    }

    //  Buscar por ID
    public AddressWithUserDTO findById(UUID addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        return toWithUserDTO(address);
    }

    private AddressListDTO toListDTO(Address address) {
        return AddressListDTO.builder()
                .id(address.getId())
                .nickname(address.getNickname())
                .zipCode(address.getZipCode())
                .build();
    }

    private AddressWithUserDTO toWithUserDTO(Address address) {
        return AddressWithUserDTO.builder()
                .id(address.getId())
                .nickname(address.getNickname())
                .neighborhood(address.getNeighborhood())
                .street(address.getStreet())
                .number(address.getNumber())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(address.getZipCode())
                .userId(address.getUser().getId())
                .build();
    }
}
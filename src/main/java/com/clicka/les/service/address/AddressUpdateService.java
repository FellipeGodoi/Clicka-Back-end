package com.clicka.les.service.address;

import com.clicka.les.dto.address.*;
import com.clicka.les.entity.Address;
import com.clicka.les.repository.user.AddressRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressUpdateService {

    private final AddressRepository addressRepository;

    public AddressListDTO execute(UUID userId, UUID addressId, AddressCreateDTO dto) {

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        if (!address.getUser().getId().equals(userId)) {
            throw new RuntimeException("Endereço não pertence ao usuário");
        }

        if (!address.getNickname().equals(dto.getNickname()) &&
                addressRepository.existsByUserIdAndNickname(userId, dto.getNickname())) {
            throw new RuntimeException("Apelido já existe para esse usuário");
        }

        address.setNickname(dto.getNickname());
        address.setNeighborhood(dto.getNeighborhood());
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZipCode(dto.getZipCode());

        addressRepository.save(address);

        return AddressListDTO.builder()
                .id(address.getId())
                .nickname(address.getNickname())
                .zipCode(address.getZipCode())
                .build();
    }
}
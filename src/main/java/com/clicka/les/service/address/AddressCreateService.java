package com.clicka.les.service.address;

import com.clicka.les.dto.address.*;
import com.clicka.les.entity.Address;
import com.clicka.les.entity.User;
import com.clicka.les.repository.user.AddressRepository;
import com.clicka.les.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressCreateService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressListDTO execute(UUID userId, AddressCreateDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (addressRepository.existsByUserIdAndNickname(userId, dto.getNickname())) {
            throw new RuntimeException("Apelido já existe para esse usuário");
        }

        Address address = Address.builder()
                .nickname(dto.getNickname())
                .neighborhood(dto.getNeighborhood())
                .street(dto.getStreet())
                .number(dto.getNumber())
                .city(dto.getCity())
                .state(dto.getState())
                .zipCode(dto.getZipCode())
                .user(user)
                .build();

        addressRepository.save(address);

        return toListDTO(address);
    }

    private AddressListDTO toListDTO(Address address) {
        return AddressListDTO.builder()
                .id(address.getId())
                .nickname(address.getNickname())
                .zipCode(address.getZipCode())
                .build();
    }
}
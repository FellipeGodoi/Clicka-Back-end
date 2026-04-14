package com.clicka.les.service.address;

import com.clicka.les.entity.Address;
import com.clicka.les.repository.user.AddressRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressDeleteService {

    private final AddressRepository addressRepository;

    public void execute(UUID userId, UUID addressId) {

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        if (!address.getUser().getId().equals(userId)) {
            throw new RuntimeException("Endereço não pertence ao usuário");
        }

        addressRepository.delete(address);
    }
}
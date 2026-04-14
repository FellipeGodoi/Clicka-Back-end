package com.clicka.les.service.user;

import java.util.stream.Collectors;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.clicka.les.dto.user.*;
import com.clicka.les.entity.User;
import com.clicka.les.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMyDataService {

    private final UserRepository userRepository;

    public UserMyDataResponseDTO execute(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return UserMyDataResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .credit(user.getCredit())
                .cpf(user.getCpf())
                .email(user.getEmail())
                .isActive(user.getIsActive())

                .phones(user.getPhones().stream()
                        .map(p -> PhoneResponseDTO.builder()
                                .id(p.getId())
                                .number(p.getNumber())
                                .nickname(p.getNickname())
                                .build())
                        .collect(Collectors.toList()))

                .addresses(user.getAddresses().stream()
                        .map(a -> AddressResponseDTO.builder()
                                .id(a.getId())
                                .nickname(a.getNickname())
                                .neighborhood(a.getNeighborhood())
                                .street(a.getStreet())
                                .number(a.getNumber())
                                .city(a.getCity())
                                .state(a.getState())
                                .zipCode(a.getZipCode())
                                .build())
                        .collect(Collectors.toList()))

                .cards(user.getCards().stream()
                        .map(c -> CardResponseDTO.builder()
                                .id(c.getId())
                                .cardNumber(c.getCardNumber())
                                .nickname(c.getNickname())
                                .expirationDate(c.getExpirationDate())
                                .build())
                        .collect(Collectors.toList()))

                .build();
    }
}
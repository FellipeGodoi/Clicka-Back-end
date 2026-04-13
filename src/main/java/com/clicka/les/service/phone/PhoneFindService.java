package com.clicka.les.service.phone;

import com.clicka.les.dto.phone.*;
import com.clicka.les.entity.Phone;
import com.clicka.les.repository.user.PhoneRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhoneFindService {

    private final PhoneRepository phoneRepository;

    public List<PhoneListDTO> findByUser(UUID userId) {
        return phoneRepository.findByUserId(userId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PhoneListDTO findById(UUID phoneId) {
        Phone phone = phoneRepository.findById(phoneId)
                .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));

        return toDTO(phone);
    }

    private PhoneListDTO toDTO(Phone phone) {
        return PhoneListDTO.builder()
                .id(phone.getId())
                .number(phone.getNumber())
                .nickname(phone.getNickname())
                .build();
    }
}
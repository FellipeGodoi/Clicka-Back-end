package com.clicka.les.service.phone;

import com.clicka.les.dto.phone.*;
import com.clicka.les.entity.Phone;
import com.clicka.les.repository.user.PhoneRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhoneUpdateService {

    private final PhoneRepository phoneRepository;

    public PhoneListDTO execute(UUID userId, UUID phoneId, PhoneCreateDTO dto) {

        Phone phone = phoneRepository.findById(phoneId)
                .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));

        if (!phone.getUser().getId().equals(userId)) {
            throw new RuntimeException("Telefone não pertence ao usuário");
        }

        if (!phone.getNickname().equals(dto.getNickname()) &&
                phoneRepository.existsByUserIdAndNickname(userId, dto.getNickname())) {
            throw new RuntimeException("Apelido já existe para esse usuário");
        }

        phone.setNumber(dto.getNumber());
        phone.setNickname(dto.getNickname());

        phoneRepository.save(phone);

        return PhoneListDTO.builder()
                .id(phone.getId())
                .number(phone.getNumber())
                .nickname(phone.getNickname())
                .build();
    }
}
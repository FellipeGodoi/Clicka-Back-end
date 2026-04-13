package com.clicka.les.service.phone;

import com.clicka.les.dto.phone.*;
import com.clicka.les.entity.Phone;
import com.clicka.les.entity.User;
import com.clicka.les.repository.user.PhoneRepository;
import com.clicka.les.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhoneCreateService {

    private final PhoneRepository phoneRepository;
    private final UserRepository userRepository;

    public PhoneListDTO execute(UUID userId, PhoneCreateDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (phoneRepository.existsByUserIdAndNickname(userId, dto.getNickname())) {
            throw new RuntimeException("Apelido já existe para esse usuário");
        }

        Phone phone = Phone.builder()
                .number(dto.getNumber())
                .nickname(dto.getNickname())
                .user(user)
                .build();

        phoneRepository.save(phone);

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
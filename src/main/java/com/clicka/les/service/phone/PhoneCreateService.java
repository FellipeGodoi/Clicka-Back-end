package com.clicka.les.service.phone;

import com.clicka.les.dto.phone.*;
import com.clicka.les.entity.Phone;
import com.clicka.les.entity.User;
import com.clicka.les.repository.user.PhoneRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneCreateService {

    private final PhoneRepository phoneRepository;

    public PhoneListDTO execute(User user, PhoneCreateDTO dto) {

        if (phoneRepository.existsByUserIdAndNickname(user.getId(), dto.getNickname())) {
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
package com.clicka.les.service.phone;

import com.clicka.les.entity.Phone;
import com.clicka.les.repository.user.PhoneRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhoneDeleteService {

    private final PhoneRepository phoneRepository;

    public void execute(UUID userId, UUID phoneId) {

        Phone phone = phoneRepository.findById(phoneId)
                .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));

        if (!phone.getUser().getId().equals(userId)) {
            throw new RuntimeException("Telefone não pertence ao usuário");
        }

        phoneRepository.delete(phone);
    }
}
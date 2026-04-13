package com.clicka.les.service.user;

import com.clicka.les.dto.user.*;
import com.clicka.les.entity.User;
import com.clicka.les.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserUpdateService {

    private final UserRepository userRepository;

    public UserDetailResponseDTO execute(UUID id, UserCreateDTO dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!user.getEmail().equals(dto.getEmail()) &&
                userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já em uso");
        }

        if (!user.getCpf().equals(dto.getCpf()) &&
                userRepository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("CPF já em uso");
        }

        user.setName(dto.getName());
        user.setCpf(dto.getCpf());
        user.setEmail(dto.getEmail());
        user.setIsActive(dto.getIsActive());

        userRepository.save(user);

        return toDTO(user);
    }

    private UserDetailResponseDTO toDTO(User user) {
        return UserDetailResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .cpf(user.getCpf())
                .email(user.getEmail())
                .isActive(user.getIsActive())
                .build();
    }
}
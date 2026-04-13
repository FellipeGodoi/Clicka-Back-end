package com.clicka.les.service.user;

import com.clicka.les.dto.user.*;
import com.clicka.les.entity.User;
import com.clicka.les.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreateService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailResponseDTO execute(UserCreateDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já em uso");
        }

        if (userRepository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("CPF já em uso");
        }

        User user = User.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .isAdmin(dto.getIsAdmin() != null ? dto.getIsAdmin() : false)
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .build();

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
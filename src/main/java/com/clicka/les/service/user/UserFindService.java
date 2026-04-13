package com.clicka.les.service.user;

import com.clicka.les.dto.user.*;
import com.clicka.les.entity.User;
import com.clicka.les.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserFindService {

    private final UserRepository userRepository;


public Page<UserListResponseDTO> findAll(String search, Boolean isActive, Pageable pageable) {

    Page<User> users = userRepository.searchUsers(search, isActive, pageable);

    return users.map(this::toListDTO);
}

    public UserDetailResponseDTO findById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return toDetailDTO(user);
    }

    private UserListResponseDTO toListDTO(User user) {
        return UserListResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .isActive(user.getIsActive())
                .build();
    }

    private UserDetailResponseDTO toDetailDTO(User user) {
        return UserDetailResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .cpf(user.getCpf())
                .email(user.getEmail())
                .isActive(user.getIsActive())
                .build();
    }
}
package com.clicka.les.service.user;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clicka.les.repository.user.UserRepository;
import com.clicka.les.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPasswordUpdateService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void execute(UUID userId, String oldPassword, String newPassword) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        boolean matches = passwordEncoder.matches(oldPassword, user.getPassword());

        if (!matches) {
            throw new RuntimeException("Senha atual incorreta");
        }

        String encryptedNewPassword = passwordEncoder.encode(newPassword);

        user.setPassword(encryptedNewPassword);

        userRepository.save(user);
    }
}
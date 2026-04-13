package com.clicka.les.controller.temp;

import com.clicka.les.entity.User;
import com.clicka.les.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempAdminController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/create-admin")
    public String createAdmin() {

        if (userRepository.existsByEmail("admin@clicka.com")) {
            return "Admin já existe";
        }

        User admin = User.builder()
                .name("Admin")
                .cpf("00000000000")
                .email("admin@clicka.com")
                .password(passwordEncoder.encode("123456"))
                .isAdmin(true)
                .isActive(true)
                .build();

        userRepository.save(admin);

        return "Admin criado com sucesso";
    }
}
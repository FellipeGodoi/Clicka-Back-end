package com.clicka.les.service.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clicka.les.dto.auth.LoginDTO;
import com.clicka.les.dto.auth.LoginResponseDTO;
import com.clicka.les.entity.User;
import com.clicka.les.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponseDTO execute(LoginDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));

        boolean passwordMatches =
                passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new RuntimeException("Email ou senha inválidos");
        }

        if (!Boolean.TRUE.equals(user.getIsActive())) {
            throw new RuntimeException("Usuário inativo");
        }

        String token = jwtService.generateToken(user);

        return LoginResponseDTO.builder()
                .token(token)
                .role(user.getRole())
                .build();
    }
}
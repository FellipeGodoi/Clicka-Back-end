package com.clicka.les.controller.temp;

import com.clicka.les.entity.User;
import com.clicka.les.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempUserCreditController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/create-user-with-credit")
    public String createUserWithCredit() {

        if (userRepository.existsByEmail("user@clicka.com")) {
            return "Usuário já existe";
        }

        User user = User.builder()
                .name("Usuário Crédito")
                .cpf("77777777777")
                .email("user@clicka.com")
                .password(passwordEncoder.encode("123456"))
                .credit(new BigDecimal("100.00"))
                .isAdmin(false)
                .isActive(true)
                .build();

        userRepository.save(user);

        return "Usuário com crédito criado";
    }
}
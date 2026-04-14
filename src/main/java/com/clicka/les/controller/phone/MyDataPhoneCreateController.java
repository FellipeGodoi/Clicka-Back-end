package com.clicka.les.controller.phone;

import com.clicka.les.dto.phone.*;
import com.clicka.les.entity.User;
import com.clicka.les.repository.user.UserRepository;
import com.clicka.les.service.phone.PhoneCreateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data/phones")
@RequiredArgsConstructor
public class MyDataPhoneCreateController {

    private final PhoneCreateService phoneCreateService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> create(
            Authentication authentication,
            @RequestBody @Valid PhoneCreateDTO dto
    ) {
        try {
            String userId = authentication.getName();

            User user = userRepository.findById(UUID.fromString(userId))
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            PhoneListDTO response = phoneCreateService.execute(user, dto);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
package com.clicka.les.controller.user;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.clicka.les.dto.user.UserPasswordUpdateDTO;
import com.clicka.les.service.user.UserPasswordUpdateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserPasswordUpdateController {

    private final UserPasswordUpdateService userPasswordUpdateService;

    @PutMapping("/my-data/new-password")
    public ResponseEntity<?> updatePassword(
            Authentication authentication,
            @RequestBody @Valid UserPasswordUpdateDTO dto
    ) {
        try {

            UUID userId = UUID.fromString(authentication.getName());

            userPasswordUpdateService.execute(
                    userId,
                    dto.getOldPassword(),
                    dto.getNewPassword()
            );

            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
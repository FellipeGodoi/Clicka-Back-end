package com.clicka.les.controller.user;

import com.clicka.les.dto.user.*;
import com.clicka.les.service.user.UserUpdateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data")
@RequiredArgsConstructor
public class UserUpdateController {

    private final UserUpdateService userUpdateService;

    @PutMapping
    public ResponseEntity<?> update(
            @RequestParam UUID id,
            @RequestBody @Valid UserCreateDTO dto
    ) {
        try {
            UserDetailResponseDTO response = userUpdateService.execute(id, dto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
package com.clicka.les.controller.user.mydata;

import com.clicka.les.dto.user.*;
import com.clicka.les.service.user.UserUpdateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data")
@RequiredArgsConstructor
public class UserUpdateController {

    private final UserUpdateService userUpdateService;

    @PutMapping
    public ResponseEntity<UserDetailResponseDTO> update(
            Authentication authentication,
            @RequestBody @Valid UserUpdateDTO dto
    ) {

        UUID userId = UUID.fromString(authentication.getName());

        UserDetailResponseDTO response =
                userUpdateService.execute(userId, dto);

        return ResponseEntity.ok(response);
    }
}
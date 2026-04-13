package com.clicka.les.controller.user;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.clicka.les.service.user.UserMyDataService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserMyDataController {

    private final UserMyDataService userMyDataService;

    @GetMapping("/my-data")
    public ResponseEntity<?> myData(Authentication authentication) {

        UUID userId = UUID.fromString(authentication.getName());

        return ResponseEntity.ok(userMyDataService.execute(userId));
    }
}
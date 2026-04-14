package com.clicka.les.controller.phone.mydata;

import com.clicka.les.service.phone.PhoneDeleteService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data/phones")
@RequiredArgsConstructor
public class MyDataPhoneDeleteController {

    private final PhoneDeleteService phoneDeleteService;

    @DeleteMapping("/{phoneId}")
    public ResponseEntity<Void> delete(
            Authentication authentication,
            @PathVariable UUID phoneId
    ) {

        UUID userId = UUID.fromString(authentication.getName());

        phoneDeleteService.execute(userId, phoneId);

        return ResponseEntity.noContent().build();
    }
}
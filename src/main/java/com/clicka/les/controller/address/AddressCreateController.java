package com.clicka.les.controller.address;

import com.clicka.les.dto.address.*;
import com.clicka.les.service.address.AddressCreateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/addresses")
@RequiredArgsConstructor
public class AddressCreateController {

    private final AddressCreateService addressCreateService;

    @PostMapping
    public ResponseEntity<?> create(
            @PathVariable UUID userId,
            @RequestBody @Valid AddressCreateDTO dto
    ) {
        try {
            return ResponseEntity.ok(
                    addressCreateService.execute(userId, dto)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
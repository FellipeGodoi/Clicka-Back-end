package com.clicka.les.controller.address;

import com.clicka.les.dto.address.*;
import com.clicka.les.service.address.AddressUpdateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressUpdateController {

    private final AddressUpdateService addressUpdateService;

    @PutMapping("/{addressId}")
    public ResponseEntity<?> update(
            @PathVariable UUID addressId,
            @RequestBody @Valid AddressCreateDTO dto
    ) {
        try {
            return ResponseEntity.ok(
                    addressUpdateService.execute(addressId, dto)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
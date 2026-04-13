package com.clicka.les.controller.address;

import com.clicka.les.service.address.AddressFindService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressFindOneController {

    private final AddressFindService addressFindService;

    @GetMapping("/{addressId}")
    public ResponseEntity<?> findById(@PathVariable UUID addressId) {
        try {
            return ResponseEntity.ok(
                    addressFindService.findById(addressId)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
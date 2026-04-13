package com.clicka.les.controller.address;

import com.clicka.les.dto.address.*;
import com.clicka.les.service.address.AddressFindService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/addresses")
@RequiredArgsConstructor
public class AddressFindAllByUserController {

    private final AddressFindService addressFindService;

    @GetMapping
    public ResponseEntity<List<AddressListDTO>> findByUser(
            @PathVariable UUID userId
    ) {
        return ResponseEntity.ok(
                addressFindService.findByUser(userId)
        );
    }
}
package com.clicka.les.controller.address.mydata;

import com.clicka.les.service.address.AddressDeleteService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data/addresses")
@RequiredArgsConstructor
public class MyDataAddressDeleteController {

    private final AddressDeleteService addressDeleteService;

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> delete(
            Authentication authentication,
            @PathVariable UUID addressId
    ) {

        UUID userId = UUID.fromString(authentication.getName());

        addressDeleteService.execute(userId, addressId);

        return ResponseEntity.noContent().build();
    }
}
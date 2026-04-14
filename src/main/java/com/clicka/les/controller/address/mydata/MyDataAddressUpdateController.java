package com.clicka.les.controller.address.mydata;

import com.clicka.les.dto.address.*;
import com.clicka.les.service.address.AddressUpdateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data/addresses")
@RequiredArgsConstructor
public class MyDataAddressUpdateController {

    private final AddressUpdateService addressUpdateService;

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressListDTO> update(
            Authentication authentication,
            @PathVariable UUID addressId,
            @RequestBody @Valid AddressCreateDTO dto
    ) {

        UUID userId = UUID.fromString(authentication.getName());

        AddressListDTO response =
                addressUpdateService.execute(userId, addressId, dto);

        return ResponseEntity.ok(response);
    }
}
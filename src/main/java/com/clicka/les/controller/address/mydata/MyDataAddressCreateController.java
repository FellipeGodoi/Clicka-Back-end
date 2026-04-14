package com.clicka.les.controller.address.mydata;

import com.clicka.les.dto.address.*;
import com.clicka.les.service.address.AddressCreateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data/addresses")
@RequiredArgsConstructor
public class MyDataAddressCreateController {

    private final AddressCreateService addressCreateService;

    @PostMapping
    public ResponseEntity<AddressListDTO> create(
            Authentication authentication,
            @RequestBody @Valid AddressCreateDTO dto
    ) {

        UUID userId = UUID.fromString(authentication.getName());

        AddressListDTO response =
                addressCreateService.execute(userId, dto);

        return ResponseEntity.ok(response);
    }
}
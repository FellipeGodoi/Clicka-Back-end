package com.clicka.les.controller.phone.mydata;

import com.clicka.les.dto.phone.*;
import com.clicka.les.service.phone.PhoneUpdateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data/phones")
@RequiredArgsConstructor
public class MyDataPhoneUpdateController {

    private final PhoneUpdateService phoneUpdateService;

    @PutMapping("/{phoneId}")
    public ResponseEntity<PhoneListDTO> update(
            Authentication authentication,
            @PathVariable UUID phoneId,
            @RequestBody @Valid PhoneCreateDTO dto
    ) {

        UUID userId = UUID.fromString(authentication.getName());

        PhoneListDTO response =
                phoneUpdateService.execute(userId, phoneId, dto);

        return ResponseEntity.ok(response);
    }
}
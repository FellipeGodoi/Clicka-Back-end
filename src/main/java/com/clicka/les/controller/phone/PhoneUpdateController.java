package com.clicka.les.controller.phone;

import com.clicka.les.dto.phone.*;
import com.clicka.les.service.phone.PhoneUpdateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/phones")
@RequiredArgsConstructor
public class PhoneUpdateController {

    private final PhoneUpdateService phoneUpdateService;

    @PutMapping("/{phoneId}")
    public ResponseEntity<?> update(
            @PathVariable UUID phoneId,
            @RequestBody @Valid PhoneCreateDTO dto
    ) {
        try {
            return ResponseEntity.ok(
                    phoneUpdateService.execute(phoneId, dto)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
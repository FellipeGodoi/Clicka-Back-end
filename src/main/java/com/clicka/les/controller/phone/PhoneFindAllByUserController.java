package com.clicka.les.controller.phone;

import com.clicka.les.dto.phone.*;
import com.clicka.les.service.phone.PhoneFindService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/phones")
@RequiredArgsConstructor
public class PhoneFindAllByUserController {

    private final PhoneFindService phoneFindService;

    @GetMapping
    public ResponseEntity<List<PhoneListDTO>> findByUser(
            @PathVariable UUID userId
    ) {
        return ResponseEntity.ok(
                phoneFindService.findByUser(userId)
        );
    }
}
package com.clicka.les.controller.phone;

import com.clicka.les.dto.phone.*;
import com.clicka.les.service.phone.PhoneCreateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/phones")
@RequiredArgsConstructor
public class PhoneCreateController {

//    private final PhoneCreateService phoneCreateService;
//
//    @PostMapping
//    public ResponseEntity<?> create(
//            @PathVariable UUID userId,
//            @RequestBody @Valid PhoneCreateDTO dto
//    ) {
//        try {
//            return ResponseEntity.ok(
//                    phoneCreateService.execute(userId, dto)
//            );
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}
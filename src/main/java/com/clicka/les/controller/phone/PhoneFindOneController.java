package com.clicka.les.controller.phone;

import com.clicka.les.service.phone.PhoneFindService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/phones")
@RequiredArgsConstructor
public class PhoneFindOneController {

    private final PhoneFindService phoneFindService;

    @GetMapping("/{phoneId}")
    public ResponseEntity<?> findById(@PathVariable UUID phoneId) {
        try {
            return ResponseEntity.ok(
                    phoneFindService.findById(phoneId)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
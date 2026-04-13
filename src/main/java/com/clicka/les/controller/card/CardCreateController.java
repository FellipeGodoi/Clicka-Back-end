package com.clicka.les.controller.card;

import com.clicka.les.dto.card.*;
import com.clicka.les.service.card.CardCreateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/cards")
@RequiredArgsConstructor
public class CardCreateController {

    private final CardCreateService cardCreateService;

    @PostMapping
    public ResponseEntity<?> create(
            @PathVariable UUID userId,
            @RequestBody @Valid CardCreateDTO dto
    ) {
        try {
            return ResponseEntity.ok(
                    cardCreateService.execute(userId, dto)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
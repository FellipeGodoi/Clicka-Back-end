package com.clicka.les.controller.card;

import com.clicka.les.service.card.CardFindService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardFindOneController {

    private final CardFindService cardFindService;

    @GetMapping("/{cardId}")
    public ResponseEntity<?> findById(@PathVariable UUID cardId) {
        try {
            return ResponseEntity.ok(
                    cardFindService.findById(cardId)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
package com.clicka.les.controller.card;

import com.clicka.les.dto.card.*;
import com.clicka.les.service.card.CardFindService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/cards")
@RequiredArgsConstructor
public class CardFindAllByUserController {

    private final CardFindService cardFindService;

    @GetMapping
    public ResponseEntity<List<CardListDTO>> findByUser(
            @PathVariable UUID userId
    ) {
        return ResponseEntity.ok(
                cardFindService.findByUser(userId)
        );
    }
}
package com.clicka.les.controller.card;

import com.clicka.les.dto.card.*;
import com.clicka.les.service.card.CardCreateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data/cards")
@RequiredArgsConstructor
public class MyDataCardCreateController {

    private final CardCreateService cardCreateService;

    @PostMapping
    public ResponseEntity<CardListDTO> create(
            Authentication authentication,
            @RequestBody @Valid CardCreateDTO dto
    ) {

        UUID userId = UUID.fromString(authentication.getName());

        CardListDTO response = cardCreateService.execute(userId, dto);

        return ResponseEntity.ok(response);
    }
}
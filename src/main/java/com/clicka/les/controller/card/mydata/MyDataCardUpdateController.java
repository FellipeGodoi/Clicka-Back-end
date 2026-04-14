package com.clicka.les.controller.card.mydata;

import com.clicka.les.dto.card.*;
import com.clicka.les.service.card.CardUpdateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data/cards")
@RequiredArgsConstructor
public class MyDataCardUpdateController {

    private final CardUpdateService cardUpdateService;

    @PutMapping("/{cardId}")
    public ResponseEntity<CardListDTO> update(
            Authentication authentication,
            @PathVariable UUID cardId,
            @RequestBody @Valid CardCreateDTO dto
    ) {

        UUID userId = UUID.fromString(authentication.getName());

        CardListDTO response =
                cardUpdateService.execute(userId, cardId, dto);

        return ResponseEntity.ok(response);
    }
}
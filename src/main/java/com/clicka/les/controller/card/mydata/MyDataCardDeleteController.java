package com.clicka.les.controller.card.mydata;

import com.clicka.les.service.card.CardDeleteService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data/cards")
@RequiredArgsConstructor
public class MyDataCardDeleteController {

    private final CardDeleteService cardDeleteService;

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> delete(
            Authentication authentication,
            @PathVariable UUID cardId
    ) {

        UUID userId = UUID.fromString(authentication.getName());

        cardDeleteService.execute(userId, cardId);

        return ResponseEntity.noContent().build();
    }
}
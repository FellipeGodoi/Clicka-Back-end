package com.clicka.les.controller.card;

import com.clicka.les.dto.card.*;
import com.clicka.les.service.card.CardUpdateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardUpdateController {

//    private final CardUpdateService cardUpdateService;
//
//    @PutMapping("/{cardId}")
//    public ResponseEntity<?> update(
//            @PathVariable UUID cardId,
//            @RequestBody @Valid CardCreateDTO dto
//    ) {
//        try {
//            return ResponseEntity.ok(
//                    cardUpdateService.execute(cardId, dto)
//            );
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}
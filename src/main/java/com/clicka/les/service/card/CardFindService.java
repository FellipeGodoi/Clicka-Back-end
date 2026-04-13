package com.clicka.les.service.card;

import com.clicka.les.dto.card.*;
import com.clicka.les.entity.Card;
import com.clicka.les.repository.user.CardRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardFindService {

    private final CardRepository cardRepository;

    public List<CardListDTO> findByUser(UUID userId) {
        return cardRepository.findByUserId(userId)
                .stream()
                .map(this::toListDTO)
                .collect(Collectors.toList());
    }

    public CardWithUserDTO findById(UUID cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));

        return toWithUserDTO(card);
    }

    private CardListDTO toListDTO(Card card) {
        return CardListDTO.builder()
                .id(card.getId())
                .cardNumber(card.getCardNumber())
                .nickname(card.getNickname())
                .expirationDate(card.getExpirationDate())
                .build();
    }

    private CardWithUserDTO toWithUserDTO(Card card) {
        return CardWithUserDTO.builder()
                .id(card.getId())
                .cardNumber(card.getCardNumber())
                .nickname(card.getNickname())
                .expirationDate(card.getExpirationDate())
                .userId(card.getUser().getId())
                .build();
    }
}
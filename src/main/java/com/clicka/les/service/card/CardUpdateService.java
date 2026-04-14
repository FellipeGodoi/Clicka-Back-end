package com.clicka.les.service.card;

import com.clicka.les.dto.card.*;
import com.clicka.les.entity.Card;
import com.clicka.les.repository.user.CardRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardUpdateService {

    private final CardRepository cardRepository;

    public CardListDTO execute(UUID userId, UUID cardId, CardCreateDTO dto) {

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));

        if (!card.getUser().getId().equals(userId)) {
            throw new RuntimeException("Cartão não pertence ao usuário");
        }

        if (!card.getNickname().equals(dto.getNickname()) &&
                cardRepository.existsByUserIdAndNickname(userId, dto.getNickname())) {
            throw new RuntimeException("Apelido já existe para esse usuário");
        }

        card.setCardNumber(dto.getCardNumber());
        card.setCvv(dto.getCvv());
        card.setNickname(dto.getNickname());
        card.setExpirationDate(dto.getExpirationDate());

        cardRepository.save(card);

        return CardListDTO.builder()
                .id(card.getId())
                .cardNumber(card.getCardNumber())
                .nickname(card.getNickname())
                .expirationDate(card.getExpirationDate())
                .build();
    }
}
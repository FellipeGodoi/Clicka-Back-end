package com.clicka.les.service.card;

import com.clicka.les.entity.Card;
import com.clicka.les.repository.user.CardRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardDeleteService {

    private final CardRepository cardRepository;

    public void execute(UUID userId, UUID cardId) {

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));

        if (!card.getUser().getId().equals(userId)) {
            throw new RuntimeException("Cartão não pertence ao usuário");
        }

        cardRepository.delete(card);
    }
}
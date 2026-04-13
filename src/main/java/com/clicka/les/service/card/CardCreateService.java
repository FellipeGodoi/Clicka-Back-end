package com.clicka.les.service.card;

import com.clicka.les.dto.card.*;
import com.clicka.les.entity.Card;
import com.clicka.les.entity.User;
import com.clicka.les.repository.user.CardRepository;
import com.clicka.les.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardCreateService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public CardListDTO execute(UUID userId, CardCreateDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (cardRepository.existsByUserIdAndNickname(userId, dto.getNickname())) {
            throw new RuntimeException("Apelido já existe para esse usuário");
        }

        Card card = Card.builder()
                .cardNumber(dto.getCardNumber())
                .cvv(dto.getCvv())
                .nickname(dto.getNickname())
                .expirationDate(dto.getExpirationDate())
                .user(user)
                .build();

        cardRepository.save(card);

        return toListDTO(card);
    }

    private CardListDTO toListDTO(Card card) {
        return CardListDTO.builder()
                .id(card.getId())
                .cardNumber(card.getCardNumber())
                .nickname(card.getNickname())
                .expirationDate(card.getExpirationDate())
                .build();
    }
}
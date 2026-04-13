package com.clicka.les.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clicka.les.entity.Card;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    boolean existsByUserIdAndNickname(UUID userId, String nickname);
    List<Card> findByUserId(UUID userId);
}
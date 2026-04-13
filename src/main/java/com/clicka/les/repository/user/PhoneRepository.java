package com.clicka.les.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clicka.les.entity.Phone;

import java.util.List;
import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, UUID> {
    boolean existsByUserIdAndNickname(UUID userId, String nickname);
    List<Phone> findByUserId(UUID userId);
}
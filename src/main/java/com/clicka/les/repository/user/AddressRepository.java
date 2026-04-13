package com.clicka.les.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clicka.les.entity.Address;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    boolean existsByUserIdAndNickname(UUID userId, String nickname);
    List<Address> findByUserId(UUID userId);
}
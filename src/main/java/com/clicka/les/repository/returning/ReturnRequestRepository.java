package com.clicka.les.repository.returning;

import com.clicka.les.entity.returning.ReturnRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReturnRequestRepository
        extends JpaRepository<ReturnRequest, UUID> {
    List<ReturnRequest> findByOrderUserIdOrderByCreatedAtDesc(UUID userId);
}
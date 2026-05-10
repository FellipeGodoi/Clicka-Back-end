package com.clicka.les.repository.returning;

import com.clicka.les.entity.returning.ReturnItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReturnItemRepository
        extends JpaRepository<ReturnItem, UUID> {

    List<ReturnItem> findByOrderItemId(UUID orderItemId);
}
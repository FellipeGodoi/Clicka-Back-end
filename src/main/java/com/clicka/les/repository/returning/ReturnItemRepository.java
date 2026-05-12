package com.clicka.les.repository.returning;

import com.clicka.les.entity.returning.ReturnItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ReturnItemRepository
        extends JpaRepository<ReturnItem, UUID> {

    List<ReturnItem> findByOrderItemId(UUID orderItemId);

    @Query("""
    SELECT ri
    FROM ReturnItem ri
    WHERE ri.orderItem.id = :orderItemId
    AND ri.returnRequest.status <> 'REJECTED'
""")
    List<ReturnItem> findValidReturnsByOrderItemId(UUID orderItemId);
}
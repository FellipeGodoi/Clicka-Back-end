package com.clicka.les.repository.user;
import com.clicka.les.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import com.clicka.les.entity.order.Order;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUserIdAndStatus(UUID userId, OrderStatus status);

    List<Order> findByUserId(UUID userId);
}

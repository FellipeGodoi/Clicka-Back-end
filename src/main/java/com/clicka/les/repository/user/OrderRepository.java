package com.clicka.les.repository.user;
import com.clicka.les.entity.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.clicka.les.entity.order.Order;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUserIdAndStatus(UUID userId, OrderStatus status);

    List<Order> findByUserId(UUID userId);

    Page<Order> findAllByOrderByCreatedAtDesc(
            Pageable pageable
    );

    Page<Order> findByStatusOrderByCreatedAtDesc(
            OrderStatus status,
            Pageable pageable
    );

    @Query("""
    SELECT o
    FROM Order o
    JOIN o.user u
    WHERE
        LOWER(str(o.id)) LIKE LOWER(CONCAT(:search, '%'))
        OR u.cpf LIKE CONCAT(:search, '%')
    ORDER BY o.createdAt DESC
""")
    Page<Order> searchOrders(
            String search,
            Pageable pageable
    );

    @Query("""
    SELECT o
    FROM Order o
    JOIN o.user u
    WHERE
        (
            LOWER(str(o.id)) LIKE LOWER(CONCAT(:search, '%'))
            OR u.cpf LIKE CONCAT(:search, '%')
        )
        AND o.status = :status
    ORDER BY o.createdAt DESC
""")
    Page<Order> searchOrdersByStatus(
            String search,
            OrderStatus status,
            Pageable pageable
    );
}

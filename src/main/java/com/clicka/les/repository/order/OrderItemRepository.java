package com.clicka.les.repository.order;

import com.clicka.les.entity.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

    @Query(value = """
        SELECT
            CAST(o.created_at AS DATE) AS period,
            oi.product_id,
            oi.product_name,
            SUM(oi.quantity) AS quantity_sold,
            SUM(oi.subtotal) / NULLIF(SUM(oi.quantity), 0) AS average_price
        FROM order_items oi
        INNER JOIN orders o
            ON oi.order_id = o.id
        WHERE
            oi.product_id IN (:products)
            AND o.created_at >= :startDate
            AND o.created_at < :endDate
        GROUP BY
            CAST(o.created_at AS DATE),
            oi.product_id,
            oi.product_name
        ORDER BY period
        """, nativeQuery = true)
    List<Object[]> salesByDay(
            @Param("products") List<String> products,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query(value = """
        SELECT
            TO_CHAR(o.created_at, 'YYYY-MM') AS period,
            oi.product_id,
            oi.product_name,
            SUM(oi.quantity) AS quantity_sold,
            SUM(oi.subtotal) / NULLIF(SUM(oi.quantity), 0) AS average_price
        FROM order_items oi
        INNER JOIN orders o
            ON oi.order_id = o.id
        WHERE
            oi.product_id IN (:products)
            AND o.created_at >= :startDate
            AND o.created_at < :endDate
        GROUP BY
            TO_CHAR(o.created_at, 'YYYY-MM'),
            oi.product_id,
            oi.product_name
        ORDER BY period
        """, nativeQuery = true)
    List<Object[]> salesByMonth(
            @Param("products") List<String> products,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
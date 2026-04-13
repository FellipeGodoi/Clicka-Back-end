package com.clicka.les.repository.user;
import org.springframework.data.jpa.repository.JpaRepository;

import com.clicka.les.entity.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}

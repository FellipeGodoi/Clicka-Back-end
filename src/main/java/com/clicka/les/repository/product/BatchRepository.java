package com.clicka.les.repository.product;

import com.clicka.les.entity.product.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BatchRepository extends JpaRepository<Batch, UUID> {
}
package com.clicka.les.entity.product;

import com.clicka.les.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "batches")
@Getter
@Setter
public class Batch extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String code;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantityReceived;

    @Column(name = "quantity_sold")
    private Integer quantitySold = 0;

    private LocalDateTime receivedAt;

    public Integer getAvailableQuantity() {
        return quantityReceived - (quantitySold != null ? quantitySold : 0);
    }

    public void reserve(int quantity) {

        if (getAvailableQuantity() < quantity) {
            throw new RuntimeException("Estoque insuficiente no lote");
        }

        if (this.quantitySold == null) {
            this.quantitySold = 0;
        }

        this.quantitySold += quantity;
    }
}
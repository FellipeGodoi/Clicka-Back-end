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

    private Integer quantitySold;

    private LocalDateTime receivedAt;

    public Integer getAvailableQuantity() {
        return quantityReceived - (quantitySold != null ? quantitySold : 0);
    }
}
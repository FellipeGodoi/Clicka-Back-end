package com.clicka.les.entity.order;

import com.clicka.les.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPayment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String cardLastDigits;

    private String nickname;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
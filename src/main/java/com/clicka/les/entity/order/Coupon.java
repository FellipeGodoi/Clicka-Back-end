package com.clicka.les.entity.order;

import com.clicka.les.entity.base.BaseEntity;
import com.clicka.les.entity.enums.CouponType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "coupons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    private CouponType type;

    @Column(nullable = false)
    private BigDecimal value;

    private BigDecimal minPurchaseAmount;

    private Boolean active;

    private LocalDateTime expirationDate;

    private Integer usageLimit;

    private Integer usedCount;
}
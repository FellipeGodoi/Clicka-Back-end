package com.clicka.les.entity.returning;

import com.clicka.les.entity.base.BaseEntity;
import com.clicka.les.entity.enums.ReturnStatus;
import com.clicka.les.entity.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "return_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private ReturnStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(mappedBy = "returnRequest", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ReturnItem> items = new ArrayList<>();
}
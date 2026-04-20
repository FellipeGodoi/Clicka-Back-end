package com.clicka.les.entity.order;

import com.clicka.les.entity.Phone;
import com.clicka.les.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "order_phones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPhone extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String number;

    private String nickname;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public static OrderPhone from(Phone phone, Order order) {
        return OrderPhone.builder()
                .number(phone.getNumber())
                .nickname(phone.getNickname())
                .order(order)
                .build();
    }
}
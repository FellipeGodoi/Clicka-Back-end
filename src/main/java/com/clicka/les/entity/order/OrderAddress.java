package com.clicka.les.entity.order;

import com.clicka.les.entity.Address;
import com.clicka.les.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "order_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderAddress extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nickname;
    private String neighborhood;
    private String street;
    private String number;
    private String city;
    private String state;
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public static OrderAddress from(Address address, Order order) {
        return OrderAddress.builder()
                .nickname(address.getNickname())
                .neighborhood(address.getNeighborhood())
                .street(address.getStreet())
                .number(address.getNumber())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(address.getZipCode())
                .order(order)
                .build();
    }

}
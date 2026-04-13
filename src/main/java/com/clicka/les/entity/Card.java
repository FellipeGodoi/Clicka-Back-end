package com.clicka.les.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

import com.clicka.les.entity.base.BaseEntity;

@Entity
@Table(name = "cards",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "nickname"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String cardNumber;

    private String cvv;

    private String nickname;

    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
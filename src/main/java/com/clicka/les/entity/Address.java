package com.clicka.les.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import com.clicka.les.entity.base.BaseEntity;

@Entity
@Table(name = "addresses",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "nickname"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address extends BaseEntity{

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
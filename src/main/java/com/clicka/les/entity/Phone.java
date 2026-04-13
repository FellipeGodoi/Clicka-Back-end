package com.clicka.les.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import com.clicka.les.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "phones",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "nickname"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phone extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String number;

    private String nickname;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

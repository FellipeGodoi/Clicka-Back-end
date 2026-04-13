package com.clicka.les.dto.card;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardWithUserDTO {

    private UUID id;
    private String cardNumber;
    private String nickname;
    private LocalDate expirationDate;

    private UUID userId;
}
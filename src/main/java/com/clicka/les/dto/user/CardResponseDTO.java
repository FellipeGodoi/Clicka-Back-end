package com.clicka.les.dto.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class CardResponseDTO {

    private UUID id;
    private String cardNumber;
    private String nickname;
    private LocalDate expirationDate;
}
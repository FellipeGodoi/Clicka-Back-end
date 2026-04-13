package com.clicka.les.dto.card;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardCreateDTO {

    @NotBlank
    private String cardNumber;

    @NotBlank
    private String cvv;

    @NotBlank
    private String nickname;

    @NotNull
    private LocalDate expirationDate;
}
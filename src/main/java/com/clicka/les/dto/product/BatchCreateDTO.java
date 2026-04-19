package com.clicka.les.dto.product;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchCreateDTO {

    @NotBlank
    private String productId;

    @NotBlank
    private String code;

    @NotNull
    @Positive(message = "A quantidade deve ser maior que zero")
    private Integer quantityReceived;

    private LocalDateTime receivedAt;
}
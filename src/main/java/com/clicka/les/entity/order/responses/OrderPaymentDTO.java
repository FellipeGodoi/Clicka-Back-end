package com.clicka.les.entity.order.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderPaymentDTO {
    private String cardLastDigits;
    private String nickname;
    private BigDecimal amount;
}
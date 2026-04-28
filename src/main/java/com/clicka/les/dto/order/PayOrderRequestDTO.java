package com.clicka.les.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PayOrderRequestDTO {

    private List<PaymentItem> payments;

    @Getter
    @Setter
    public static class PaymentItem {
        private UUID cardId;
        private BigDecimal amount;
    }
}
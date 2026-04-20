package com.clicka.les.entity.order.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderItemDTO {

    private String productId;
    private String productName;
    private String productType;

    private String batchCode;

    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal subtotal;
}
package com.clicka.les.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderItemDTO {
    private String productId;
    private Integer quantity;
}
package com.clicka.les.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CreateOrderRequestDTO {

    private List<CreateOrderItemDTO> items;

    private String addressId;

    private String phoneId;

    private String couponCode;

    private BigDecimal creditToUse;
}
package com.clicka.les.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShippingSimulationRequestDTO {

    private String addressId;
    private List<CreateOrderItemDTO> items;
}
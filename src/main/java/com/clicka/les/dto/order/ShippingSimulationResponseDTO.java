package com.clicka.les.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ShippingSimulationResponseDTO {

    private BigDecimal shippingCost;
    private LocalDate estimatedDeliveryDate;
}
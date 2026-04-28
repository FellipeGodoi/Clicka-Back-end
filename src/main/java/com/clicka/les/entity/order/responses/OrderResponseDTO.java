package com.clicka.les.entity.order.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponseDTO {

    private String id;
    private String status;

    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal creditUsed;
    private BigDecimal finalAmount;

    private String couponCode;

    private List<OrderItemDTO> items;

    private OrderAddressDTO address;
    private OrderPhoneDTO phone;

    private LocalDate estimatedDeliveryDate;
    private LocalDate deliveredAt;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
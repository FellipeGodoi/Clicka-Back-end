package com.clicka.les.service.order.mapper;

import com.clicka.les.entity.order.Order;
import com.clicka.les.entity.order.OrderItem;
import com.clicka.les.entity.order.responses.*;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponseDTO toDTO(Order order) {

        return OrderResponseDTO.builder()
                .id(order.getId().toString())
                .status(order.getStatus().name())
                .totalAmount(order.getTotalAmount())
                .discountAmount(order.getDiscountAmount())
                .creditUsed(order.getCreditUsed())
                .finalAmount(order.getFinalAmount())
                .couponCode(order.getCouponCode())

                .items(
                        order.getItems() == null
                                ? List.of()
                                : order.getItems()
                                .stream()
                                .map(OrderMapper::toItemDTO)
                                .collect(Collectors.toList())
                )

                .address(toAddressDTO(order))
                .phone(toPhoneDTO(order))
                .estimatedDeliveryDate(order.getEstimatedDeliveryDate())
                .deliveredAt(order.getDeliveredAt())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    private static OrderItemDTO toItemDTO(OrderItem item) {
        return OrderItemDTO.builder()
                .productId(item.getProductId())
                .productName(item.getProductName())
                .productType(item.getProductType())
                .batchCode(item.getBatchCode())
                .unitPrice(item.getUnitPrice())
                .quantity(item.getQuantity())
                .subtotal(item.getSubtotal())
                .build();
    }

    private static OrderAddressDTO toAddressDTO(Order order) {
        if (order.getAddress() == null) return null;

        return OrderAddressDTO.builder()
                .nickname(order.getAddress().getNickname())
                .neighborhood(order.getAddress().getNeighborhood())
                .street(order.getAddress().getStreet())
                .number(order.getAddress().getNumber())
                .city(order.getAddress().getCity())
                .state(order.getAddress().getState())
                .zipCode(order.getAddress().getZipCode())
                .build();
    }

    private static OrderPhoneDTO toPhoneDTO(Order order) {
        if (order.getPhone() == null) return null;

        return OrderPhoneDTO.builder()
                .number(order.getPhone().getNumber())
                .nickname(order.getPhone().getNickname())
                .build();
    }
}
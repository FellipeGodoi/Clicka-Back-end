package com.clicka.les.service.order.mapper;

import com.clicka.les.entity.enums.ReturnStatus;
import com.clicka.les.entity.order.Order;
import com.clicka.les.entity.order.OrderItem;
import com.clicka.les.entity.order.responses.*;
import com.clicka.les.entity.returning.ReturnItem;
import com.clicka.les.repository.returning.ReturnItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ReturnItemRepository returnItemRepository;

    public OrderResponseDTO toDTO(Order order) {

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
                                .map(this::toItemDTO)
                                .toList()
                )

                .address(toAddressDTO(order))
                .phone(toPhoneDTO(order))

                .estimatedDeliveryDate(order.getEstimatedDeliveryDate())
                .deliveredAt(order.getDeliveredAt())

                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())

                .build();
    }

    private OrderItemDTO toItemDTO(OrderItem item) {

        int alreadyReturned = returnItemRepository
                .findValidReturnsByOrderItemId(item.getId())
                .stream()
                .mapToInt(ReturnItem::getQuantity)
                .sum();

        int available =
                item.getQuantity() - alreadyReturned;

        return OrderItemDTO.builder()
                .id(item.getId().toString())
                .productId(item.getProductId())
                .productName(item.getProductName())
                .productType(item.getProductType())
                .batchCode(item.getBatchCode())
                .unitPrice(item.getUnitPrice())
                .quantity(item.getQuantity())
                .subtotal(item.getSubtotal())

                .availableReturnQuantity(
                        Math.max(available, 0)
                )

                .canReturn(available > 0)

                .build();
    }

    private OrderAddressDTO toAddressDTO(Order order) {

        if (order.getAddress() == null) {
            return null;
        }

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

    private OrderPhoneDTO toPhoneDTO(Order order) {

        if (order.getPhone() == null) {
            return null;
        }

        return OrderPhoneDTO.builder()
                .number(order.getPhone().getNumber())
                .nickname(order.getPhone().getNickname())
                .build();
    }
}
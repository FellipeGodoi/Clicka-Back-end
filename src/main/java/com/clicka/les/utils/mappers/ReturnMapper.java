package com.clicka.les.utils.mappers;

import com.clicka.les.dto.returning.ReturnItemResponseDTO;
import com.clicka.les.dto.returning.ReturnResponseDTO;
import com.clicka.les.entity.returning.ReturnItem;
import com.clicka.les.entity.returning.ReturnRequest;


import java.util.stream.Collectors;

public class ReturnMapper {

    public static ReturnResponseDTO toDTO(ReturnRequest request) {

        return ReturnResponseDTO.builder()
                .id(request.getId().toString())
                .orderId(request.getOrder().getId().toString())
                .status(request.getStatus().name())
                .createdAt(request.getCreatedAt())
                .items(
                        request.getItems()
                                .stream()
                                .map(ReturnMapper::toItemDTO)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public static ReturnItemResponseDTO toItemDTO(ReturnItem item) {

        return ReturnItemResponseDTO.builder()
                .id(item.getId().toString())
                .orderItemId(item.getOrderItem().getId().toString())
                .productName(item.getOrderItem().getProductName())
                .quantity(item.getQuantity())
                .build();
    }
}
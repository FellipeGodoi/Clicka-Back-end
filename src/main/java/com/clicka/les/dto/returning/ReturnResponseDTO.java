package com.clicka.les.dto.returning;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ReturnResponseDTO {

    private String id;

    private String orderId;

    private String status;

    private LocalDateTime createdAt;

    private List<ReturnItemResponseDTO> items;
}
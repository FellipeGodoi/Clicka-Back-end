package com.clicka.les.dto.returning;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReturnItemResponseDTO {

    private String id;

    private String orderItemId;

    private String productName;

    private Integer quantity;
}
package com.clicka.les.dto.returning;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateReturnRequestDTO {

    private String orderId;

    private List<CreateReturnItemDTO> items;
}
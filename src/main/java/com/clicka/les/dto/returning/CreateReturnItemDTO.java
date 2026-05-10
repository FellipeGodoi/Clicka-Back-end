package com.clicka.les.dto.returning;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReturnItemDTO {

    private String orderItemId;
    private Integer quantity;
}
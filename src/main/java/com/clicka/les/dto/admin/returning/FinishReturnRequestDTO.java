package com.clicka.les.dto.admin.returning;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinishReturnRequestDTO {

    private Boolean approved;

    private String rejectionReason;
}
package com.clicka.les.entity.order.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderPhoneDTO {

    private String number;
    private String nickname;
}
package com.clicka.les.entity.order.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderAddressDTO {

    private String nickname;
    private String neighborhood;
    private String street;
    private String number;
    private String city;
    private String state;
    private String zipCode;
}
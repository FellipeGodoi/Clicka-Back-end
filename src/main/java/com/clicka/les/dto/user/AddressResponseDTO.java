package com.clicka.les.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AddressResponseDTO {

    private UUID id;
    private String nickname;
    private String neighborhood;
    private String street;
    private String number;
    private String city;
    private String state;
    private String zipCode;
}
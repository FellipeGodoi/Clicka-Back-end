package com.clicka.les.dto.address;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressWithUserDTO {

    private UUID id;
    private String nickname;

    private String neighborhood;
    private String street;
    private String number;
    private String city;
    private String state;
    private String zipCode;

    private UUID userId;
}
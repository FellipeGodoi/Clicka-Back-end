package com.clicka.les.dto.address;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressListDTO {

    private UUID id;
    private String nickname;
    private String zipCode;
}

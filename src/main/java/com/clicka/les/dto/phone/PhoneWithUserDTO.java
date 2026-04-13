package com.clicka.les.dto.phone;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneWithUserDTO {

    private UUID id;
    private String number;
    private String nickname;
    private UUID userId;
}
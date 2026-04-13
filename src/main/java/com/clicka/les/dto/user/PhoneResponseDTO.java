package com.clicka.les.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PhoneResponseDTO {

    private UUID id;
    private String number;
    private String nickname;
}
package com.clicka.les.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserMyDataResponseDTO {

    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private Boolean isActive;

    private List<PhoneResponseDTO> phones;
    private List<AddressResponseDTO> addresses;
    private List<CardResponseDTO> cards;
}
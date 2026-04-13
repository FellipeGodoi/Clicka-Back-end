package com.clicka.les.dto.user;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserListResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private Boolean isActive;
}
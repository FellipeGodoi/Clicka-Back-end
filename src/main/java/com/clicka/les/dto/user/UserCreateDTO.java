package com.clicka.les.dto.user;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String cpf;

    @Email
    private String email;

    @NotBlank
    private String password;

    private Boolean isAdmin;

    private Boolean isActive;
}
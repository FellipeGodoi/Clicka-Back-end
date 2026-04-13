package com.clicka.les.dto.user;

import lombok.Data;

@Data
public class UserPasswordUpdateDTO {

    private String oldPassword;
    private String newPassword;
}
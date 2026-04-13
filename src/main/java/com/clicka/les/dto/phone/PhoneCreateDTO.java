package com.clicka.les.dto.phone;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneCreateDTO {

    @NotBlank
    private String number;

    @NotBlank
    private String nickname;
}
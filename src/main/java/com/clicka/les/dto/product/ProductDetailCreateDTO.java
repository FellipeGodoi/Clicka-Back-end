package com.clicka.les.dto.product;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailCreateDTO {

    @NotBlank
    private String attribute;

    @NotBlank
    private String value;
}
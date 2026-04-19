package com.clicka.les.dto.product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateDTO {

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private BigDecimal defaultPrice;

    @PositiveOrZero
    private BigDecimal promotionalPrice;

    @NotBlank
    private String description;

    @NotBlank
    private String type;

    @NotBlank
    private String imageUrl;

    @Valid
    private List<ProductDetailCreateDTO> details;

    private Set<@NotBlank String> tags;
}
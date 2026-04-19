package com.clicka.les.dto.product;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductListItemDTO {

    private UUID id;

    private String name;

    private String imageUrl;

    private BigDecimal defaultPrice;

    private BigDecimal promotionalPrice;

    private Integer stockQuantity;

    private Set<String> tags;
}
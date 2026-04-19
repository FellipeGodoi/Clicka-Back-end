package com.clicka.les.dto.product;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailResponseDTO {

    private String id;

    private String name;

    private BigDecimal defaultPrice;

    private BigDecimal promotionalPrice;

    private String description;

    private String imageUrl;

    private String type;

    private Integer stockQuantity;

    private Set<String> tags;

    private List<ProductDetailItemDTO> details;
}
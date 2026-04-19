package com.clicka.les.dto.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductFilterDTO {

    private String search;
    private String type;

    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    private Boolean includeOutOfStock = true;
    private String orderByPrice = "ASC";
}
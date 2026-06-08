package com.clicka.les.dto.dashboard;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategorySalesReportResponse {

    private String period;

    private String category;

    private Long quantitySold;

    private BigDecimal averagePrice;
}
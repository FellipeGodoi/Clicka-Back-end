package com.clicka.les.dto.dashboard;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesReportResponse {

    private String period;

    private String productId;

    private String productName;

    private Long quantitySold;

    private BigDecimal averagePrice;
}
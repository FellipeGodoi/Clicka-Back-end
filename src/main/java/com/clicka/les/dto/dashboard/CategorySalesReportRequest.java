package com.clicka.les.dto.dashboard;

import com.clicka.les.entity.enums.GroupBy;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CategorySalesReportRequest {

    private List<String> categories;

    private LocalDate startDate;

    private LocalDate endDate;

    private GroupBy groupBy;
}
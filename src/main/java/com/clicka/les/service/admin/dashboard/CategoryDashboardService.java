package com.clicka.les.service.admin.dashboard;

import com.clicka.les.dto.dashboard.CategorySalesReportRequest;
import com.clicka.les.dto.dashboard.CategorySalesReportResponse;
import com.clicka.les.entity.enums.GroupBy;
import com.clicka.les.repository.order.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryDashboardService {

    private final OrderItemRepository repository;

    public List<CategorySalesReportResponse> salesReport(
            CategorySalesReportRequest request
    ) {

        List<Object[]> result;

        if (request.getGroupBy() == GroupBy.MONTH) {
            result = repository.categorySalesByMonth(
                    request.getCategories(),
                    request.getStartDate(),
                    request.getEndDate()
            );
        } else {
            result = repository.categorySalesByDay(
                    request.getCategories(),
                    request.getStartDate(),
                    request.getEndDate()
            );
        }

        return result.stream()
                .map(row -> CategorySalesReportResponse.builder()
                        .period(row[0].toString())
                        .category(row[1].toString())
                        .quantitySold(((Number) row[2]).longValue())
                        .averagePrice((BigDecimal) row[3])
                        .build())
                .toList();
    }
}
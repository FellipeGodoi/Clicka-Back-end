package com.clicka.les.service.admin.dashboard;

import com.clicka.les.dto.dashboard.SalesReportRequest;
import com.clicka.les.dto.dashboard.SalesReportResponse;
import com.clicka.les.entity.enums.GroupBy;
import com.clicka.les.repository.order.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final OrderItemRepository repository;

    public List<SalesReportResponse> salesReport(
            SalesReportRequest request
    ) {

        List<Object[]> result;

        if (request.getGroupBy() == GroupBy.MONTH) {
            result = repository.salesByMonth(
                    request.getProducts(),
                    request.getStartDate(),
                    request.getEndDate()
            );
        } else {
            result = repository.salesByDay(
                    request.getProducts(),
                    request.getStartDate(),
                    request.getEndDate()
            );
        }

        return result.stream()
                .map(row -> SalesReportResponse.builder()
                        .period(row[0].toString())
                        .productId(row[1].toString())
                        .productName(row[2].toString())
                        .quantitySold(((Number) row[3]).longValue())
                        .averagePrice((BigDecimal) row[4])
                        .build())
                .toList();
    }
}
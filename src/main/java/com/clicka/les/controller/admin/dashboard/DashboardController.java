package com.clicka.les.controller.admin.dashboard;

import com.clicka.les.dto.dashboard.SalesReportRequest;
import com.clicka.les.dto.dashboard.SalesReportResponse;
import com.clicka.les.service.admin.dashboard.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @PostMapping("/sales")
    public ResponseEntity<List<SalesReportResponse>> getSalesReport(
            @RequestBody SalesReportRequest request
    ) {

        List<SalesReportResponse> response =
                dashboardService.salesReport(request);

        return ResponseEntity.ok(response);
    }
}
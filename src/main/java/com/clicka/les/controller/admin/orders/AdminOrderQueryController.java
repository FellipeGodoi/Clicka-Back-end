package com.clicka.les.controller.admin.orders;

import com.clicka.les.entity.enums.OrderStatus;
import com.clicka.les.entity.order.responses.OrderResponseDTO;
import com.clicka.les.service.admin.orders.GetAllOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderQueryController{

    private final GetAllOrdersService service;

    @GetMapping
    public Page<OrderResponseDTO> getOrders(

            @RequestParam(required = false)
            OrderStatus status,

            @RequestParam(required = false)
            String search,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size
    ) {

        return service.get(
                status,
                search,
                page,
                size
        );
    }
}
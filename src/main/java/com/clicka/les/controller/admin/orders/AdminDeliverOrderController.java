package com.clicka.les.controller.admin.orders;

import com.clicka.les.service.admin.orders.DeliverOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminDeliverOrderController {

    private final DeliverOrderService service;

    @PatchMapping("/{id}/deliver")
    public void deliver(
            @PathVariable String id
    ) {

        service.execute(
                UUID.fromString(id)
        );
    }
}
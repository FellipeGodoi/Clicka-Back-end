package com.clicka.les.controller.admin.orders;

import com.clicka.les.service.admin.orders.ShipOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminShipOrderController {

    private final ShipOrderService service;

    @PatchMapping("/{id}/ship")
    public void ship(
            @PathVariable String id
    ) {

        service.execute(
                UUID.fromString(id)
        );
    }
}
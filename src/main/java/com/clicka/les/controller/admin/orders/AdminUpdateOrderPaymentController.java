package com.clicka.les.controller.admin.orders;

import com.clicka.les.dto.admin.orders.UpdatePaymentStatusDTO;
import com.clicka.les.service.admin.orders.UpdateOrderPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminUpdateOrderPaymentController {

    private final UpdateOrderPaymentService service;

    @PatchMapping("/{id}/payment")
    public void updatePayment(
            @PathVariable String id,
            @RequestBody UpdatePaymentStatusDTO dto
    ) {

        service.execute(
                UUID.fromString(id),
                dto
        );
    }
}
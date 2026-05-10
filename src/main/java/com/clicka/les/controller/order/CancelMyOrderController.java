package com.clicka.les.controller.order;

import com.clicka.les.service.order.CancelMyOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data/order")
@RequiredArgsConstructor
public class CancelMyOrderController {

    private final CancelMyOrderService cancelMyOrderService;

    @PatchMapping("/{id}/cancel")
    public void cancelOrder(
            @PathVariable String id,
            Authentication authentication
    ) {

        UUID userId = UUID.fromString(authentication.getName());
        UUID orderId = UUID.fromString(id);

        cancelMyOrderService.execute(orderId, userId);
    }
}
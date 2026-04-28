package com.clicka.les.controller.order;

import com.clicka.les.entity.order.responses.OrderResponseDTO;
import com.clicka.les.service.user.GetMyOrderByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data/order")
@RequiredArgsConstructor
public class GetMyOrderController {

    private final GetMyOrderByIdService getOrderByIdService;

    @GetMapping("/{id}")
    public OrderResponseDTO getById(
            @PathVariable String id,
            Authentication authentication
    ) {

        UUID userId = UUID.fromString(authentication.getName());
        UUID orderId = UUID.fromString(id);

        return getOrderByIdService.execute(orderId, userId);
    }
}
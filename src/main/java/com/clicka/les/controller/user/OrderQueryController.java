package com.clicka.les.controller.user;

import com.clicka.les.entity.enums.OrderStatus;
import com.clicka.les.entity.order.responses.OrderResponseDTO;
import com.clicka.les.service.user.GetUserOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/my-data/orders")
@RequiredArgsConstructor
public class OrderQueryController {

    private final GetUserOrdersService getUserOrdersService;

    @GetMapping
    public List<OrderResponseDTO> getOrders(
            Authentication authentication,
            @RequestParam(required = false) OrderStatus status
    ) {

        UUID userId = UUID.fromString(authentication.getName());

        return getUserOrdersService.get(userId, status);
    }
}
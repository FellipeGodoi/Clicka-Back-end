package com.clicka.les.controller.order;

import com.clicka.les.dto.order.CreateOrderRequestDTO;
import com.clicka.les.entity.User;
import com.clicka.les.entity.order.Order;
import com.clicka.les.entity.order.responses.OrderResponseDTO;
import com.clicka.les.service.order.CreateOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/my-data/orders")
@RequiredArgsConstructor
public class NewOrderController {

    private final CreateOrderService createOrderService;

    @PostMapping
    public OrderResponseDTO create(@RequestBody CreateOrderRequestDTO dto,
                                   Authentication authentication) {

        UUID userId = UUID.fromString(authentication.getName());

        User user = new User();
        user.setId(userId);

        return createOrderService.create(dto, user);
    }
}
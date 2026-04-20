package com.clicka.les.controller.order;

import com.clicka.les.dto.order.PayOrderRequestDTO;
import com.clicka.les.entity.order.Order;
import com.clicka.les.entity.order.responses.OrderResponseDTO;
import com.clicka.les.service.order.PayOrderService;
import com.clicka.les.service.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my-data/orders")
@RequiredArgsConstructor
public class OrderPaymentController {

    private final PayOrderService payOrderService;

    @PostMapping("/{orderId}/pay")
    public OrderResponseDTO pay(
            @PathVariable UUID orderId,
            @RequestBody PayOrderRequestDTO dto
    ) {

        Order order = payOrderService.process(orderId, dto);

        return OrderMapper.toDTO(order);
    }
}
package com.clicka.les.service.user;

import com.clicka.les.entity.order.Order;
import com.clicka.les.entity.order.responses.OrderResponseDTO;
import com.clicka.les.repository.user.OrderRepository;
import com.clicka.les.service.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetMyOrderByIdService {

    private final OrderRepository orderRepository;

    public OrderResponseDTO execute(UUID orderId, UUID userId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (!order.getUser().getId().equals(userId)) {
            throw new RuntimeException("Acesso negado a este pedido");
        }

        return OrderMapper.toDTO(order);
    }
}
package com.clicka.les.service.user;

import com.clicka.les.entity.enums.OrderStatus;
import com.clicka.les.entity.order.responses.OrderResponseDTO;
import com.clicka.les.repository.user.OrderRepository;
import com.clicka.les.service.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetUserOrdersService {

    private final OrderRepository orderRepository;

    public List<OrderResponseDTO> get(UUID userId, OrderStatus status) {

        if (status != null) {
            return orderRepository.findByUserIdAndStatus(userId, status)
                    .stream()
                    .map(OrderMapper::toDTO)
                    .toList();
        }

        return orderRepository.findByUserId(userId)
                .stream()
                .map(OrderMapper::toDTO)
                .toList();
    }
}
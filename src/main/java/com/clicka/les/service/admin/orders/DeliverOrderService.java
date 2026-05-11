package com.clicka.les.service.admin.orders;

import com.clicka.les.config.exceptions.BadRequestException;
import com.clicka.les.entity.enums.OrderStatus;
import com.clicka.les.entity.order.Order;
import com.clicka.les.repository.user.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliverOrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void execute(UUID orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new BadRequestException("Pedido não encontrado"));

        if (order.getStatus() != OrderStatus.SHIPPED) {
            throw new BadRequestException(
                    "Somente pedidos enviados podem ser entregues"
            );
        }

        order.setStatus(OrderStatus.DELIVERED);

        order.setDeliveredAt(LocalDate.now());

        orderRepository.save(order);
    }
}
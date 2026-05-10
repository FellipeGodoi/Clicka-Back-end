package com.clicka.les.service.order;

import com.clicka.les.config.exceptions.BadRequestException;
import com.clicka.les.entity.User;
import com.clicka.les.entity.enums.OrderStatus;
import com.clicka.les.entity.order.Order;
import com.clicka.les.repository.user.OrderRepository;
import com.clicka.les.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CancelMyOrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional
    public void execute(UUID orderId, UUID userId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new BadRequestException("Pedido não encontrado"));

        if (!order.getUser().getId().equals(userId)) {
            throw new BadRequestException(
                    "Acesso negado a este pedido"
            );
        }

        if (!order.getStatus().canBeCancelled()) {
            throw new BadRequestException(
                    "Este pedido não pode ser cancelado"
            );
        }

        if (order.getStatus() == OrderStatus.AWAITING_APPROVAL) {

            User user = order.getUser();

            BigDecimal currentCredit = user.getCredit() == null
                    ? BigDecimal.ZERO
                    : user.getCredit();

            BigDecimal refundValue = order.getFinalAmount() == null
                    ? BigDecimal.ZERO
                    : order.getFinalAmount();

            user.setCredit(currentCredit.add(refundValue));

            userRepository.save(user);
        }

        order.setStatus(OrderStatus.CANCELLED);

        orderRepository.save(order);
    }
}
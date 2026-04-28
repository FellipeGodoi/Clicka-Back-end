package com.clicka.les.service.order;

import com.clicka.les.dto.order.PayOrderRequestDTO;
import com.clicka.les.entity.Card;
import com.clicka.les.entity.enums.OrderStatus;
import com.clicka.les.entity.order.Order;
import com.clicka.les.entity.order.OrderPayment;
import com.clicka.les.repository.user.CardRepository;
import com.clicka.les.repository.user.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PayOrderService {

    private final OrderRepository orderRepository;
    private final CardRepository cardRepository;

    public Order process(UUID orderId, PayOrderRequestDTO dto) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (order.getStatus() == OrderStatus.AWAITING_APPROVAL) {
            throw new RuntimeException("Pedido já pago");
        }

        BigDecimal totalPaid = BigDecimal.ZERO;

        if (order.getPayments() == null) {
            order.setPayments(new ArrayList<>());
        }

        for (PayOrderRequestDTO.PaymentItem item : dto.getPayments()) {

            Card card = cardRepository.findById(item.getCardId())
                    .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));

            if (item.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("Valor inválido");
            }

            OrderPayment payment = OrderPayment.builder()
                    .order(order)
                    .amount(item.getAmount())
                    .cardLastDigits(card.getCardNumber().substring(card.getCardNumber().length() - 4))
                    .nickname(card.getNickname())
                    .build();

            order.getPayments().add(payment);

            totalPaid = totalPaid.add(item.getAmount());
        }

        if (totalPaid.compareTo(order.getFinalAmount()) < 0) {
            throw new RuntimeException("Pagamento insuficiente");
        }

        order.setStatus(OrderStatus.AWAITING_APPROVAL);

        return orderRepository.save(order);
    }
}
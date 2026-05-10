package com.clicka.les.service.admin.orders;

import com.clicka.les.config.exceptions.BadRequestException;
import com.clicka.les.dto.admin.orders.UpdatePaymentStatusDTO;
import com.clicka.les.entity.enums.OrderStatus;
import com.clicka.les.entity.order.Order;
import com.clicka.les.entity.order.OrderItem;
import com.clicka.les.entity.product.Batch;
import com.clicka.les.repository.product.BatchRepository;
import com.clicka.les.repository.user.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateOrderPaymentService {

    private final OrderRepository orderRepository;
    private final BatchRepository batchRepository;

    @Transactional
    public void execute(
            UUID orderId,
            UpdatePaymentStatusDTO dto
    ) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new BadRequestException("Pedido não encontrado"));

        if (order.getStatus() != OrderStatus.AWAITING_APPROVAL) {
            throw new BadRequestException(
                    "Pedido não está aguardando aprovação"
            );
        }

        if (Boolean.TRUE.equals(dto.getApproved())) {

            order.setStatus(OrderStatus.APPROVED);

            orderRepository.save(order);

            return;
        }

        for (OrderItem item : order.getItems()) {

            Batch batch = batchRepository
                    .findByCode(item.getBatchCode())
                    .orElseThrow(() ->
                            new BadRequestException("Lote não encontrado"));

            batch.setQuantitySold(
                    batch.getQuantitySold() - item.getQuantity()
            );

            batchRepository.save(batch);
        }

        order.setStatus(OrderStatus.REFUSED_PAYMENT);

        orderRepository.save(order);
    }
}
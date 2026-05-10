package com.clicka.les.service.returning;

import com.clicka.les.config.exceptions.BadRequestException;
import com.clicka.les.dto.returning.CreateReturnItemDTO;
import com.clicka.les.dto.returning.CreateReturnRequestDTO;
import com.clicka.les.entity.enums.OrderStatus;
import com.clicka.les.entity.enums.ReturnStatus;
import com.clicka.les.entity.order.Order;
import com.clicka.les.entity.order.OrderItem;
import com.clicka.les.entity.returning.ReturnItem;
import com.clicka.les.entity.returning.ReturnRequest;
import com.clicka.les.repository.returning.ReturnItemRepository;
import com.clicka.les.repository.returning.ReturnRequestRepository;
import com.clicka.les.repository.user.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateReturnRequestService {

    private final OrderRepository orderRepository;
    private final ReturnRequestRepository returnRequestRepository;
    private final ReturnItemRepository returnItemRepository;

    @Transactional
    public void execute(CreateReturnRequestDTO dto, UUID userId) {

        UUID orderId = UUID.fromString(dto.getOrderId());

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new BadRequestException("Pedido não encontrado"));

        if (!order.getUser().getId().equals(userId)) {
            throw new BadRequestException("Pedido não pertence ao usuário");
        }

        if (order.getStatus() != OrderStatus.DELIVERED) {
            throw new BadRequestException(
                    "Somente pedidos entregues podem ser devolvidos"
            );
        }

        ReturnRequest returnRequest = ReturnRequest.builder()
                .order(order)
                .status(ReturnStatus.REQUESTED)
                .build();

        for (CreateReturnItemDTO itemDTO : dto.getItems()) {

            UUID orderItemId = UUID.fromString(itemDTO.getOrderItemId());

            OrderItem orderItem = order.getItems()
                    .stream()
                    .filter(item -> item.getId().equals(orderItemId))
                    .findFirst()
                    .orElseThrow(() ->
                            new BadRequestException(
                                    "Item não pertence ao pedido"
                            ));

            if (itemDTO.getQuantity() <= 0) {
                throw new BadRequestException(
                        "Quantidade inválida"
                );
            }

            int alreadyReturned = returnItemRepository
                    .findByOrderItemId(orderItemId)
                    .stream()
                    .mapToInt(ReturnItem::getQuantity)
                    .sum();

            int availableQuantity =
                    orderItem.getQuantity() - alreadyReturned;

            if (itemDTO.getQuantity() > availableQuantity) {
                throw new BadRequestException(
                        "Quantidade para devolução indisponível"
                );
            }

            ReturnItem returnItem = ReturnItem.builder()
                    .orderItem(orderItem)
                    .quantity(itemDTO.getQuantity())
                    .returnRequest(returnRequest)
                    .build();

            returnRequest.getItems().add(returnItem);
        }

        returnRequestRepository.save(returnRequest);
    }
}
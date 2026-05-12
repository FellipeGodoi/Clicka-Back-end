package com.clicka.les.service.admin.orders;

import com.clicka.les.entity.enums.OrderStatus;
import com.clicka.les.entity.order.responses.OrderResponseDTO;
import com.clicka.les.repository.user.OrderRepository;
import com.clicka.les.service.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllOrdersService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Page<OrderResponseDTO> get(
            OrderStatus status,
            String search,
            int page,
            int size
    ) {

        PageRequest pageable =
                PageRequest.of(page, size);

        if (search != null && !search.isBlank()) {

            if (status != null) {

                return orderRepository
                        .searchOrdersByStatus(
                                search,
                                status,
                                pageable
                        )
                        .map(orderMapper::toDTO);
            }

            return orderRepository
                    .searchOrders(search, pageable)
                    .map(orderMapper::toDTO);
        }

        if (status != null) {

            return orderRepository
                    .findByStatusOrderByCreatedAtDesc(
                            status,
                            pageable
                    )
                    .map(orderMapper::toDTO);
        }

        return orderRepository
                .findAllByOrderByCreatedAtDesc(pageable)
                .map(orderMapper::toDTO);
    }
}
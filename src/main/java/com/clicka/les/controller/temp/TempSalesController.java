package com.clicka.les.controller.temp;

import com.clicka.les.entity.User;
import com.clicka.les.entity.enums.OrderStatus;
import com.clicka.les.entity.order.Order;
import com.clicka.les.entity.order.OrderItem;
import com.clicka.les.repository.user.OrderRepository;
import com.clicka.les.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempSalesController {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;


    @PostMapping("/generate-sales")
    public String generateSales() {

        User user = userRepository.findById(
                UUID.fromString("96aeb280-6ee4-4778-8ab3-8e82f0ca5fc5")
        ).orElseThrow();

        String productId = "b095bad9-32c5-497e-bf3b-8fea90810c3f";
        for (int month = 1; month <= 12; month++) {

            createOrder(user, productId, month, 5);
            createOrder(user, productId, month, 15);
            createOrder(user, productId, month, 25);
        }

        return "36 vendas geradas com sucesso";
    }

    private void createOrder(
            User user,
            String productId,
            int month,
            int day
    ) {

        int quantity = (int) (Math.random() * 5) + 1;

        BigDecimal unitPrice = BigDecimal.valueOf(349.90);

        BigDecimal subtotal = unitPrice.multiply(
                BigDecimal.valueOf(quantity)
        );

        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.DELIVERED)
                .totalAmount(subtotal)
                .discountAmount(BigDecimal.ZERO)
                .creditUsed(BigDecimal.ZERO)
                .finalAmount(subtotal)
                .build();

        OrderItem item = OrderItem.builder()
                .productId(productId)
                .productName("Fone Gamer Elite")
                .productType("HEADSET")
                .quantity(quantity)
                .unitPrice(unitPrice)
                .subtotal(subtotal)
                .order(order)
                .build();

        order.getItems().add(item);

        LocalDateTime date = LocalDateTime.of(
                2026,
                month,
                day,
                10,
                0
        );

        order.setCreatedAt(date);
        order.setUpdatedAt(date);

        item.setCreatedAt(date);
        item.setUpdatedAt(date);

        orderRepository.save(order);
    }
}
package com.clicka.les.service.order;

import com.clicka.les.dto.order.CreateOrderItemDTO;
import com.clicka.les.dto.order.CreateOrderRequestDTO;
import com.clicka.les.entity.Address;
import com.clicka.les.entity.Phone;
import com.clicka.les.entity.User;
import com.clicka.les.entity.enums.OrderStatus;
import com.clicka.les.entity.order.Order;
import com.clicka.les.entity.order.OrderAddress;
import com.clicka.les.entity.order.OrderItem;
import com.clicka.les.entity.order.OrderPhone;
import com.clicka.les.entity.order.responses.OrderResponseDTO;
import com.clicka.les.entity.product.Batch;
import com.clicka.les.entity.product.Product;
import com.clicka.les.repository.product.ProductRepository;
import com.clicka.les.repository.user.AddressRepository;
import com.clicka.les.repository.user.OrderRepository;
import com.clicka.les.repository.user.PhoneRepository;
import com.clicka.les.repository.user.UserRepository;
import com.clicka.les.service.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateOrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ShippingService shippingService;
    private final CouponService couponService;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;
    private final UserRepository userRepository;

    public OrderResponseDTO create(CreateOrderRequestDTO dto, User user) {

        Address address = addressRepository.findById(UUID.fromString(dto.getAddressId()))
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        Phone phone = phoneRepository.findById(UUID.fromString(dto.getPhoneId()))
                .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));

        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.AWAITING_PAYMENT)
                .couponCode(dto.getCouponCode())
                .creditUsed(dto.getCreditToUse() != null ? dto.getCreditToUse() : BigDecimal.ZERO)
                .build();

        BigDecimal subtotal = BigDecimal.ZERO;
        int totalItems = 0;

        List<OrderItem> items = new ArrayList<>();

        for (CreateOrderItemDTO itemDTO : dto.getItems()) {

            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            BigDecimal price = product.getPromotionalPrice() != null
                    ? product.getPromotionalPrice()
                    : product.getDefaultPrice();

            int qty = itemDTO.getQuantity();

            String batchCode = resolveBatchCode(product, qty);

            OrderItem item = OrderItem.builder()
                    .order(order)
                    .productId(product.getId())
                    .productName(product.getName())
                    .productType(product.getType())
                    .unitPrice(price)
                    .quantity(qty)
                    .subtotal(price.multiply(BigDecimal.valueOf(qty)))
                    .batchCode(batchCode)
                    .build();

            items.add(item);

            subtotal = subtotal.add(item.getSubtotal());
            totalItems += qty;
        }

        order.setItems(items);
        order.setTotalAmount(subtotal);

        String state = address.getState();
        BigDecimal shipping = shippingService.calculate(state, totalItems);

        BigDecimal discount = dto.getCouponCode() != null
                ? couponService.apply(dto.getCouponCode(), subtotal)
                : BigDecimal.ZERO;

        BigDecimal creditUsed = dto.getCreditToUse() != null
                ? dto.getCreditToUse()
                : BigDecimal.ZERO;

        User managedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        creditUsed = dto.getCreditToUse() != null
                ? dto.getCreditToUse()
                : BigDecimal.ZERO;

        if (creditUsed.compareTo(BigDecimal.ZERO) > 0) {

            if (managedUser.getCredit() == null) {
                managedUser.setCredit(BigDecimal.ZERO);
            }

            if (managedUser.getCredit().compareTo(creditUsed) < 0) {
                throw new RuntimeException("Crédito insuficiente");
            }

            managedUser.setCredit(
                    managedUser.getCredit().subtract(creditUsed)
            );

            userRepository.save(managedUser);
        }

        BigDecimal finalAmount = subtotal
                .subtract(discount)
                .add(shipping)
                .subtract(creditUsed);

        if (finalAmount.compareTo(BigDecimal.ZERO) < 0) {
            finalAmount = BigDecimal.ZERO;
        }

        order.setDiscountAmount(discount);
        order.setFinalAmount(finalAmount);

        order.setAddress(OrderAddress.from(address, order));
        order.setPhone(OrderPhone.from(phone, order));

        Order saved = orderRepository.save(order);

        return OrderMapper.toDTO(saved);
    }

    private String resolveBatchCode(Product product, int quantity) {

        for (Batch batch : product.getBatches()) {
            if (batch.getAvailableQuantity() >= quantity) {
                return batch.getCode();
            }
        }

        throw new RuntimeException("Sem estoque em lote suficiente");
    }
}
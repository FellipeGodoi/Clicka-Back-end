package com.clicka.les.service.order;

import com.clicka.les.dto.order.CreateOrderItemDTO;

import com.clicka.les.dto.order.ShippingSimulationRequestDTO;
import com.clicka.les.dto.order.ShippingSimulationResponseDTO;
import com.clicka.les.entity.Address;
import com.clicka.les.repository.user.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShippingSimulationService {

    private final AddressRepository addressRepository;
    private final ShippingService shippingService;

    public ShippingSimulationResponseDTO simulate(ShippingSimulationRequestDTO dto) {

        Address address = addressRepository.findById(UUID.fromString(dto.getAddressId()))
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        int totalItems = dto.getItems().stream()
                .mapToInt(CreateOrderItemDTO::getQuantity)
                .sum();

        BigDecimal shippingCost = shippingService.calculate(
                address.getState(),
                totalItems
        );

        LocalDate estimatedDate = shippingService.estimateDelivery(
                address.getState()
        );

        return ShippingSimulationResponseDTO.builder()
                .shippingCost(shippingCost)
                .estimatedDeliveryDate(estimatedDate)
                .build();
    }
}
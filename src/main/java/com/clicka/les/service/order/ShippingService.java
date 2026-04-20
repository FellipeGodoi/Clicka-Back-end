package com.clicka.les.service.order;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ShippingService {

    public BigDecimal calculate(String state, int totalItems) {

        return switch (state.toUpperCase()) {

            case "SP" -> BigDecimal.valueOf(20)
                    .add(BigDecimal.valueOf(5L * totalItems));

            case "RJ" -> BigDecimal.valueOf(10)
                    .add(BigDecimal.valueOf(15L * totalItems));

            case "MG" -> BigDecimal.valueOf(15)
                    .add(BigDecimal.valueOf(10L * totalItems));

            case "ES" -> BigDecimal.valueOf(12)
                    .add(BigDecimal.valueOf(8L * totalItems));

            default -> BigDecimal.valueOf(25)
                    .add(BigDecimal.valueOf(10L * totalItems));
        };
    }
}
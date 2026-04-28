package com.clicka.les.service.order;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ShippingService {

    public BigDecimal calculate(String state, int totalItems) {

        BigDecimal base;
        BigDecimal perItem;

        switch (state.toUpperCase()) {
            case "SP" -> {
                base = BigDecimal.valueOf(20);
                perItem = BigDecimal.valueOf(5);
            }
            case "RJ" -> {
                base = BigDecimal.valueOf(10);
                perItem = BigDecimal.valueOf(15);
            }
            case "MG" -> {
                base = BigDecimal.valueOf(15);
                perItem = BigDecimal.valueOf(10);
            }
            case "ES" -> {
                base = BigDecimal.valueOf(12);
                perItem = BigDecimal.valueOf(8);
            }
            default -> {
                base = BigDecimal.valueOf(25);
                perItem = BigDecimal.valueOf(10);
            }
        }

        return base.add(perItem.multiply(BigDecimal.valueOf(totalItems)));
    }

    public LocalDate estimateDelivery(String state) {

        int daysToAdd;

        switch (state.toUpperCase()) {
            case "SP" -> daysToAdd = 2;
            case "RJ" -> daysToAdd = 3;
            case "MG" -> daysToAdd = 4;
            case "ES" -> daysToAdd = 5;
            default -> daysToAdd = 7;
        }

        return LocalDate.now().plusDays(daysToAdd);
    }
}
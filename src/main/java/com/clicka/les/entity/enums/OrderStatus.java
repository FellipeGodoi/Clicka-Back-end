package com.clicka.les.entity.enums;

public enum OrderStatus {

    CREATED,
    AWAITING_PAYMENT,
    AWAITING_APPROVAL,

    APPROVED,
    REFUSED_PAYMENT,

    SHIPPED,
    DELIVERED,

    CANCELLED;

    public boolean canBeCancelled() {
        return this == CREATED
                || this == AWAITING_PAYMENT
                || this == AWAITING_APPROVAL;
    }
}
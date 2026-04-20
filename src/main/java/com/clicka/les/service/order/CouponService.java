package com.clicka.les.service.order;


import com.clicka.les.entity.enums.CouponType;
import com.clicka.les.entity.order.Coupon;
import com.clicka.les.repository.order.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public BigDecimal apply(String code, BigDecimal subtotal) {

        if (code == null || code.isBlank()) {
            return BigDecimal.ZERO;
        }

        Coupon coupon = couponRepository.findByCode(code.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Cupom inválido"));

        if (Boolean.FALSE.equals(coupon.getActive())) {
            throw new RuntimeException("Cupom inativo");
        }

        if (coupon.getExpirationDate() != null &&
                coupon.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Cupom expirado");
        }

        if (coupon.getMinPurchaseAmount() != null &&
                subtotal.compareTo(coupon.getMinPurchaseAmount()) < 0) {
            throw new RuntimeException("Pedido abaixo do valor mínimo do cupom");
        }

        if (coupon.getType() == CouponType.FIXED) {
            return coupon.getValue();
        }

        if (coupon.getType() == CouponType.PERCENTAGE) {
            return subtotal.multiply(coupon.getValue().divide(BigDecimal.valueOf(100)));
        }

        return BigDecimal.ZERO;
    }
}
package com.clicka.les.controller.order;

import com.clicka.les.entity.order.Coupon;
import com.clicka.les.repository.order.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponValidateController {

    private final CouponRepository couponRepository;

    @GetMapping("/validate")
    public Coupon validate(@RequestParam String code) {

        Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cupom não encontrado"));

        if (!coupon.getActive()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cupom inativo");
        }

        if (coupon.getExpirationDate() != null &&
                coupon.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cupom expirado");
        }

        if (coupon.getUsageLimit() != null &&
                coupon.getUsedCount() >= coupon.getUsageLimit()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cupom esgotado");
        }

        return coupon;
    }
}
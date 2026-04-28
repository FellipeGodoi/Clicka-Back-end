package com.clicka.les.controller.temp;

import com.clicka.les.entity.enums.CouponType;
import com.clicka.les.entity.order.Coupon;
import com.clicka.les.repository.order.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempCouponController {

    private final CouponRepository couponRepository;

    @PostMapping("/create-coupons")
    public String createCoupons() {

        if (couponRepository.count() > 0) {
            return "Cupons já cadastrados";
        }

        Coupon c1 = Coupon.builder()
                .code("DESC10")
                .type(CouponType.PERCENTAGE)
                .value(new BigDecimal("10"))
                .minPurchaseAmount(new BigDecimal("100"))
                .active(true)
                .expirationDate(LocalDateTime.now().plusDays(30))
                .usageLimit(100)
                .usedCount(0)
                .build();

        Coupon c2 = Coupon.builder()
                .code("FIXO50")
                .type(CouponType.FIXED)
                .value(new BigDecimal("50"))
                .minPurchaseAmount(new BigDecimal("200"))
                .active(true)
                .expirationDate(LocalDateTime.now().plusDays(15))
                .usageLimit(50)
                .usedCount(0)
                .build();

        Coupon c3 = Coupon.builder()
                .code("DESC20")
                .type(CouponType.FIXED)
                .value(new BigDecimal("20"))
                .minPurchaseAmount(new BigDecimal("80"))
                .active(true)
                .expirationDate(LocalDateTime.now().plusDays(10))
                .usageLimit(200)
                .usedCount(0)
                .build();

        couponRepository.save(c1);
        couponRepository.save(c2);
        couponRepository.save(c3);

        return "Cupons criados com sucesso";
    }
}
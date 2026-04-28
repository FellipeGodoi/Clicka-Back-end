package com.clicka.les.controller.user;

import com.clicka.les.dto.order.ShippingSimulationRequestDTO;
import com.clicka.les.dto.order.ShippingSimulationResponseDTO;
import com.clicka.les.service.order.ShippingSimulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingSimulationService shippingSimulationService;

    @PostMapping("/simulate")
    public ShippingSimulationResponseDTO simulate(
            @RequestBody ShippingSimulationRequestDTO dto
    ) {
        return shippingSimulationService.simulate(dto);
    }
}
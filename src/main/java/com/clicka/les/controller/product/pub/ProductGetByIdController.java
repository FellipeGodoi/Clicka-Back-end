package com.clicka.les.controller.product.pub;

import com.clicka.les.dto.product.ProductDetailResponseDTO;
import com.clicka.les.service.product.ProductGetByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductGetByIdController {

    private final ProductGetByIdService service;

    @GetMapping("/{id}")
    public ProductDetailResponseDTO getById(@PathVariable String id) {
        return service.getById(id);
    }
}
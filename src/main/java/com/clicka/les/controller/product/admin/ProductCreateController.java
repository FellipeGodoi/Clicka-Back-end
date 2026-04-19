package com.clicka.les.controller.product.admin;

import com.clicka.les.dto.product.ProductCreateDTO;
import com.clicka.les.service.product.ProductCreateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductCreateController {

    private final ProductCreateService productCreateService;

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody ProductCreateDTO dto
    ) {
        productCreateService.create(dto);
        return ResponseEntity.ok().build();
    }
}
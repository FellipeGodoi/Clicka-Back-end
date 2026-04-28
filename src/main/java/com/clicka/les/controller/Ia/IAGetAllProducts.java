package com.clicka.les.controller.Ia;

import com.clicka.les.dto.product.ProductDetailResponseDTO;
import com.clicka.les.service.product.ProductGetAllService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/public/products")
@RequiredArgsConstructor
public class IAGetAllProducts {

    private final ProductGetAllService service;

    @GetMapping
    public List<ProductDetailResponseDTO> getAll() {
        return service.getAll();
    }
}
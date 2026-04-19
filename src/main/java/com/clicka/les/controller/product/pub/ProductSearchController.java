package com.clicka.les.controller.product.pub;

import com.clicka.les.dto.product.*;
import com.clicka.les.service.product.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductSearchController {

    private final ProductSearchService service;

    @GetMapping
    public Page<ProductListItemDTO> search(
            @ModelAttribute ProductFilterDTO filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.search(filter, page, size);
    }
}
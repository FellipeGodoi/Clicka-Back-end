package com.clicka.les.service.product;

import com.clicka.les.dto.product.*;
import com.clicka.les.entity.product.Product;
import com.clicka.les.entity.product.Tag;
import com.clicka.les.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSearchService {

    private final ProductRepository productRepository;

    public Page<ProductListItemDTO> search(ProductFilterDTO filter, int page, int size) {

        Sort sort = "ASC".equalsIgnoreCase(filter.getOrderByPrice())
                ? Sort.by("defaultPrice").ascending()
                : Sort.by("defaultPrice").descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> result = productRepository.searchProducts(
                normalize(filter.getType()),
                normalize(filter.getSearch()),
                pageable
        );

        var dtoList = result
                .stream()
                .map(this::toDTO)
                .filter(dto -> applyPriceFilter(dto, filter))
                .filter(dto -> applyStockFilter(dto, filter))
                .toList();

        return new PageImpl<>(dtoList, pageable, result.getTotalElements());
    }

    private String normalize(String value) {
        return (value == null || value.isBlank()) ? null : value;
    }

    private boolean applyPriceFilter(ProductListItemDTO dto, ProductFilterDTO filter) {

        BigDecimal price = dto.getPromotionalPrice() != null
                ? dto.getPromotionalPrice()
                : dto.getDefaultPrice();

        if (filter.getMinPrice() != null && price.compareTo(filter.getMinPrice()) < 0)
            return false;

        if (filter.getMaxPrice() != null && price.compareTo(filter.getMaxPrice()) > 0)
            return false;

        return true;
    }

    private boolean applyStockFilter(ProductListItemDTO dto, ProductFilterDTO filter) {

        if (filter.getIncludeOutOfStock() == null || filter.getIncludeOutOfStock())
            return true;

        return dto.getStockQuantity() != null && dto.getStockQuantity() > 0;
    }

    private ProductListItemDTO toDTO(Product product) {

        return ProductListItemDTO.builder()
                .id(UUID.fromString(product.getId()))
                .name(product.getName())
                .imageUrl(product.getImageUrl())
                .defaultPrice(product.getDefaultPrice())
                .promotionalPrice(product.getPromotionalPrice())
                .stockQuantity(calculateStock(product))
                .tags(product.getTags()
                        .stream()
                        .map(Tag::getName)
                        .collect(Collectors.toSet()))
                .build();
    }

    private int calculateStock(Product product) {
        return product.getBatches() == null ? 0 :
                product.getBatches()
                        .stream()
                        .mapToInt(b ->
                                (b.getQuantityReceived() != null ? b.getQuantityReceived() : 0)
                                        - (b.getQuantitySold() != null ? b.getQuantitySold() : 0)
                        )
                        .sum();
    }
}
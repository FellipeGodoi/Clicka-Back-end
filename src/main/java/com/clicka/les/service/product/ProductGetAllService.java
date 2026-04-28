package com.clicka.les.service.product;

import com.clicka.les.dto.product.*;
import com.clicka.les.entity.product.Product;
import com.clicka.les.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductGetAllService {

    private final ProductRepository repository;

    public List<ProductDetailResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private ProductDetailResponseDTO toDTO(Product product) {
        return ProductDetailResponseDTO.builder()
                .id(product.getId().toString())
                .name(product.getName())
                .defaultPrice(product.getDefaultPrice())
                .promotionalPrice(product.getPromotionalPrice())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .type(product.getType())
                .stockQuantity(calculateStock(product))
                .tags(product.getTags()
                        .stream()
                        .map(t -> t.getName())
                        .collect(Collectors.toSet()))
                .details(product.getDetails()
                        .stream()
                        .map(d -> ProductDetailItemDTO.builder()
                                .name(d.getAttribute())
                                .value(d.getValue())
                                .build())
                        .toList())
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
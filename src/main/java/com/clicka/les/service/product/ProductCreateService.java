package com.clicka.les.service.product;

import com.clicka.les.dto.product.ProductCreateDTO;
import com.clicka.les.entity.product.Product;
import com.clicka.les.entity.product.ProductDetail;
import com.clicka.les.entity.product.Tag;
import com.clicka.les.repository.product.ProductRepository;
import com.clicka.les.repository.product.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductCreateService {

    private final ProductRepository productRepository;
    private final TagRepository tagRepository;

    @Transactional
    public void create(ProductCreateDTO dto) {

        Product product = new Product();
        product.setName(dto.getName());
        product.setDefaultPrice(dto.getDefaultPrice());
        product.setPromotionalPrice(dto.getPromotionalPrice());
        product.setDescription(dto.getDescription());
        product.setType(dto.getType());
        product.setImageUrl(dto.getImageUrl());

        if (dto.getDetails() != null) {
            product.setDetails(
                    dto.getDetails().stream().map(d -> {
                        ProductDetail detail = new ProductDetail();
                        detail.setAttribute(d.getAttribute());
                        detail.setValue(d.getValue());
                        detail.setProduct(product);
                        return detail;
                    }).toList()
            );
        }

        if (dto.getTags() != null) {

            Set<Tag> tags = new HashSet<>();

            for (String tagName : dto.getTags()) {

                Tag tag = tagRepository.findByNameIgnoreCase(tagName)
                        .orElseGet(() -> {
                            Tag newTag = new Tag();
                            newTag.setName(tagName);
                            return tagRepository.save(newTag);
                        });

                tags.add(tag);
            }

            product.setTags(tags);
        }

        productRepository.save(product);
    }
}
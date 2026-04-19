package com.clicka.les.service.product;

import com.clicka.les.dto.product.BatchCreateDTO;
import com.clicka.les.entity.product.Batch;
import com.clicka.les.entity.product.Product;
import com.clicka.les.repository.product.BatchRepository;
import com.clicka.les.repository.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BatchCreateService {

    private final BatchRepository batchRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void create(BatchCreateDTO dto) {

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Batch batch = new Batch();
        batch.setProduct(product);
        batch.setCode(dto.getCode());
        batch.setQuantityReceived(dto.getQuantityReceived());
        batch.setQuantitySold(0);
        batch.setReceivedAt(
                dto.getReceivedAt() != null
                        ? dto.getReceivedAt()
                        : LocalDateTime.now()
        );

        batchRepository.save(batch);
    }
}
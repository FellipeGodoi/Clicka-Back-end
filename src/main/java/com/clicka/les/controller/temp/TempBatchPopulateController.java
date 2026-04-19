package com.clicka.les.controller.temp;

import com.clicka.les.entity.product.Batch;
import com.clicka.les.entity.product.Product;
import com.clicka.les.repository.product.BatchRepository;
import com.clicka.les.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempBatchPopulateController {

    private final ProductRepository productRepository;
    private final BatchRepository batchRepository;

    private final Random random = new Random();

    @PostMapping("/populate-batches")
    public String populate() {

        List<Product> products = productRepository.findAll();

        for (Product product : products) {

            createBatch(product);
            createBatch(product);
        }

        return "Lotes criados!";
    }

    private void createBatch(Product product) {

        Batch batch = new Batch();
        batch.setProduct(product);
        batch.setCode(randomCode());
        batch.setQuantityReceived(randomQuantity());
        batch.setQuantitySold(0);
        batch.setReceivedAt(LocalDateTime.now());

        batchRepository.save(batch);
    }

    private int randomQuantity() {
        return random.nextInt(11);
    }

    private String randomCode() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8)
                .toUpperCase();
    }
}
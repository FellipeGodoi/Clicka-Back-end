package com.clicka.les.repository.product;

import com.clicka.les.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = """
    SELECT DISTINCT p FROM Product p
    LEFT JOIN p.tags t
    LEFT JOIN p.batches b
    WHERE 
    (COALESCE(:type, '') = '' OR LOWER(p.type) = LOWER(:type))
    AND (
        COALESCE(:search, '') = '' OR
        LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) OR
        LOWER(t.name) LIKE LOWER(CONCAT('%', :search, '%')) OR
        LOWER(p.type) LIKE LOWER(CONCAT('%', :search, '%'))
    )
""",
            countQuery = """
    SELECT COUNT(DISTINCT p) FROM Product p
    LEFT JOIN p.tags t
    LEFT JOIN p.batches b
    WHERE 
    (COALESCE(:type, '') = '' OR LOWER(p.type) = LOWER(:type))
    AND (
        COALESCE(:search, '') = '' OR
        LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) OR
        LOWER(t.name) LIKE LOWER(CONCAT('%', :search, '%')) OR
        LOWER(p.type) LIKE LOWER(CONCAT('%', :search, '%'))
    )
""")
    Page<Product> searchProducts(
            @Param("type") String type,
            @Param("search") String search,
            Pageable pageable
    );
}
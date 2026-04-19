package com.clicka.les.entity.product;

import com.clicka.les.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_details")
@Getter
@Setter
public class ProductDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String attribute;

    private String value;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
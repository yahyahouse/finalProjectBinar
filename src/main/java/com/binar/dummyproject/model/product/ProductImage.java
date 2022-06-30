package com.binar.dummyproject.model.product;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "product_image_id")
    private Long productImageId;

    @Column(name = "product_image_name")
    private String productImageName;

    @Column(name ="url",columnDefinition="VARCHAR(10000)")
    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product productId;

    public ProductImage(){

    }

    public ProductImage(String productImageName){
        this.productImageName = productImageName;
    }
}

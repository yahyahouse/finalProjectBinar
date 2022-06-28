package com.binar.dummyproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_image_id")
    private Long productImageId;

    @Column(name = "product_image_name")
    private String productImageName;

    @Column(name = "product_image_file")
    private byte[] productImageFile;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product productId;

    public ProductImage(){

    }

    public ProductImage(String productImageName, byte[] productImageFile){
        this.productImageName = productImageName;
        this.productImageFile = productImageFile;
    }
}

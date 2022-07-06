package com.binar.dummyproject.model.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_image")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    private Long productImageId;

    @Column(name = "product_image_name")
    private String productImageName;

    @Column(name ="url",columnDefinition="VARCHAR(10000)")
    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product productId;

    public ProductImage(){

    }

    public ProductImage(String productImageName){
        this.productImageName = productImageName;
    }
}

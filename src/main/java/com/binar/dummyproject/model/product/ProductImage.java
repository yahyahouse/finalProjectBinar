package com.binar.dummyproject.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    private Long productImageId;

    @Column(name = "product_image_name")
    private String productImageName;

    @Column(name ="url",columnDefinition="VARCHAR(10000)")
    private String url;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private Product productId;

    public ProductImage(){

    }

    public ProductImage(String productImageName){
        this.productImageName = productImageName;
    }
}

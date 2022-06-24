package com.binar.dummyproject.model;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Data
@Entity(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_image", unique = false, nullable = false, length = 100000)
    private String productImage;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_price")
    private Integer productPrice;

    @Column(name = "product_category")
    private String productCategory;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;

}


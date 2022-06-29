package com.binar.dummyproject.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity(name = "productImage")
public class ProductImage implements Serializable {
    @Id
    @Column(name = "productImageId", nullable = false)
    private Long productImageid;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;

    @Column(name = "image1")
    private String image1;

    @Column(name = "image2")
    private String image2;

    @Column(name = "image3")
    private String image3;

    @Column(name = "image4")
    private String image4;
}

package com.binar.dummyproject.model.product;

import javax.persistence.*;

import com.binar.dummyproject.model.users.Users;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_price")
    private Integer productPrice;

    @Column(name = "product_category")
    private String productCategory;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private List<ProductImage> productImages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;
}


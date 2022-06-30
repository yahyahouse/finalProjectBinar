package com.binar.dummyproject.model;


import com.binar.dummyproject.model.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity(name = "wishlist")
public class Wishlist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id", nullable = false)
    private Long wishlistId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;

}

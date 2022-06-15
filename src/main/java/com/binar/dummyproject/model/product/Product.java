package com.binar.dummyproject.model.product;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "product_desc")
    private String productDesc;

}

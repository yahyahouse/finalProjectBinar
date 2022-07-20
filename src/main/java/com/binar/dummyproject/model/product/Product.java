package com.binar.dummyproject.model.product;

import javax.persistence.*;

import com.binar.dummyproject.model.users.Users;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Entity(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description", columnDefinition = "TEXT")
    private String productDescription;

    @Column(name = "product_price")
    private Integer productPrice;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "product_status")
    private String productStatus;

    @Column(name ="url",columnDefinition="VARCHAR(10000)")
    private String url;

    @Column(name ="url2",columnDefinition="VARCHAR(10000)")
    private String url2;

    @Column(name ="url3",columnDefinition="VARCHAR(10000)")
    private String url3;

    @Column(name ="url4",columnDefinition="VARCHAR(10000)")
    private String url4;

    @Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;
}


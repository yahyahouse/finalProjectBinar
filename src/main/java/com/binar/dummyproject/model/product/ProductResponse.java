package com.binar.dummyproject.model.product;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ProductResponse {

    private Integer userId;
    private Long productId;
    private String productName;
    private String productDescription;
    private Integer productPrice;
    private String productCategory;
    private String productStatus;
    private String url;
    private String url2;
    private String url3;
    private String url4;
    private LocalDateTime localDateTime;

    public ProductResponse() {

    }


    public ProductResponse(Integer userId, String productName, String productDescription,
                           Integer productPrice, String productCategory, String productStatus, String url,
                           String url2, String url3, String url4, LocalDateTime localDateTime) {
        this.userId = userId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productStatus = productStatus;
        this.url = url;
        this.url2 = url2;
        this.url3 = url3;
        this.url4 = url4;
        this.localDateTime = localDateTime;

    }

    public ProductResponse(Integer userId, Long productId, String productName, String productStatus) {
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.productStatus = productStatus;
    }

    public ProductResponse(Product product) {
        this.userId = product.getUserId().getUserId();
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.productPrice = product.getProductPrice();
        this.productCategory = product.getProductCategory();
        this.productStatus = product.getProductStatus();
        this.url = product.getUrl();
        this.url2 = product.getUrl2();
        this.url3 = product.getUrl3();
        this.url4 = product.getUrl4();

    }


}

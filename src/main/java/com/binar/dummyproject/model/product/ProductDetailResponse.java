package com.binar.dummyproject.model.product;

import lombok.Data;



@Data
public class ProductDetailResponse {

    private Integer userId;
    private Long productId;
    private String city;
    private String username;
    private String productName;
    private String productDescription;
    private Integer productPrice;
    private String productCategory;
    private String productStatus;
    private String url;
    private String url2;
    private String url3;
    private String url4;

    public ProductDetailResponse(){

    }



    public ProductDetailResponse(Product product){
        this.userId = product.getUserId().getUserId();
        this.city = product.getUserId().getCity();
        this.username = product.getUserId().getUsername();
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

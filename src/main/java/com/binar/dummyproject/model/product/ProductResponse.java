package com.binar.dummyproject.model.product;

import com.binar.dummyproject.model.users.Users;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

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

    public ProductResponse() {

    }

    public ProductResponse(Product product, Users users) {
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

    public ProductResponse(Integer userId, String productName, String productDescription,
                           Integer productPrice, String productCategory, String productStatus, String url,
                           String url2, String url3, String url4) {
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

    }

    public ProductResponse(Integer userId, Long productId, String productName, String productDescription, Integer productPrice, String productCategory, String productStatus, String url, String url2, String url3, String url4) {
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productStatus = productStatus;
        this.url = url;
        this.url2 = url2;
        this.url3 = url3;
        this.url4 = url4;
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
